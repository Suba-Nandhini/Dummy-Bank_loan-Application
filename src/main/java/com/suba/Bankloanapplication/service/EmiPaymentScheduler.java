package com.suba.Bankloanapplication.service;

import com.suba.Bankloanapplication.model.*;
import com.suba.Bankloanapplication.repository.CustomerRepository;
import com.suba.Bankloanapplication.repository.LoanApplicantRepository;
import com.suba.Bankloanapplication.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableScheduling
@Component
public class EmiPaymentScheduler {

    private static final double PENALTY_PERCENTAGE = 0.05;
    private static final Duration REMINDER_INTERVAL = Duration.ofDays(2);
    private static final Logger logger = LoggerFactory.getLogger(EmiPaymentScheduler.class);

    @Autowired
    private LoanApplicantService loanApplicantService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private Map<String, LocalDateTime> lastRemainderTimeStamps = new HashMap<>();
    @Autowired
    private LoanApplicantRepository loanApplicantRepository;

    @Scheduled(cron = "0 * * * * *")
    public void scheduleEmiPayment() {
        logger.info("EMI payment scheduling started.");
        try {
            List<LoanApplicant> eligibleCustomers = loanApplicantService.findEligibleCustomersWithApprovedLoan();
            logger.info("Found {} eligible customers", eligibleCustomers.size());

            for (LoanApplicant loanApplicant : eligibleCustomers) {
                processLoanApplicant(loanApplicant);
            }
        } catch (Exception e) {
            logger.error("An error occurred during EMI payment scheduling: {}", e.getMessage(), e);
        }
    }

    private void processLoanApplicant(LoanApplicant loanApplicant) {
        double emiAmount = loanApplicant.getEmiAmt();
        int tenure = loanApplicant.getTenure();
        Customer customer = customerRepository.findByAccountNumber(loanApplicant.getCustomerAccountNumber());

        if (customer == null) {
            logger.warn("Customer not found for account number {}", loanApplicant.getCustomerAccountNumber());
            return;
        }

        if (tenure > 0) {
            if (customer.getBalance() >= emiAmount) {
                processSuccessfulPayment(customer, loanApplicant, emiAmount);
            } else {
                processInsufficientFunds(customer, loanApplicant, emiAmount);
            }
        } else {
            loanApplicant.setLoanStatus(TransactionStatus.COMPLETED.name());
            loanApplicantRepository.save(loanApplicant);

        }
    }

    private void processInsufficientFunds(Customer customer, LoanApplicant loanApplicant, double emiAmount) {
        if (canSendReminderEmail(customer)) {
            sendReminderEmail(customer);
            lastRemainderTimeStamps.put(customer.getAccountNumber(), LocalDateTime.now());
        } else {
            applyPenalty(customer, emiAmount);
        }
    }

    private void applyPenalty(Customer customer, double emiAmount) {
        double penalty = emiAmount * PENALTY_PERCENTAGE;
        customer.setDebit(customer.getDebit() + penalty);
        customerRepository.save(customer);
        logger.warn("Penalty applied for the customer: {}, Penalty Amount: {}", customer.getAccountNumber(), penalty);
    }

    private void sendReminderEmail(Customer customer) {
        String messageBody = "Dear " + customer.getName() + ",\n" +
                "This is a reminder to credit your account for the EMI payment.\n" +
                "Thank you.";
        loanApplicantService.sendEmail(customer.getEmail(), "Reminder for EMI Payment", messageBody);
    }

    private boolean canSendReminderEmail(Customer customer) {
        LocalDateTime lastDateTime = lastRemainderTimeStamps.get(customer.getAccountNumber());
        return lastDateTime == null || lastDateTime.plus(REMINDER_INTERVAL).isBefore(LocalDateTime.now());
    }

    private void processSuccessfulPayment(Customer customer, LoanApplicant loanApplicant, double emiAmount) {
        customer.setBalance(customer.getBalance() - emiAmount);
        customerRepository.save(customer);

        loanApplicant.setTenure(loanApplicant.getTenure() - 1);
        loanApplicantRepository.save(loanApplicant);
        // loanApplicantService.updateLoanApplicant(loanApplicant); // Ensure this method updates the loanApplicant in the database

        saveTransaction(customer, loanApplicant, emiAmount, TransactionType.DEBIT);

        logger.info("EMI payment processed for customer: {}, New balance: {}, Remaining tenure: {}",
                customer.getAccountNumber(), customer.getBalance(), loanApplicant.getTenure());
    }

    private void saveTransaction(Customer customer, LoanApplicant loanApplicant, double emiAmount, TransactionType transactionType) {
        Transaction transaction = new Transaction();
        transaction.setCustomerId(customer.getId());
        transaction.setSenderAccountNumber(loanApplicant.getCustomerAccountNumber());
        transaction.setReceiverAccountNumber(customer.getAccountNumber());
        transaction.setAmount(emiAmount);
        transaction.setTransactionType(transactionType);
        transaction.setTransactionTime(LocalDateTime.now());
        transactionRepository.save(transaction);
    }
}
