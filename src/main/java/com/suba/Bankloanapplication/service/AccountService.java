package com.suba.Bankloanapplication.service;

import com.suba.Bankloanapplication.exception.AccountDeletionException;
import com.suba.Bankloanapplication.model.AccountDeletionRequest;
import com.suba.Bankloanapplication.model.Customer;
import com.suba.Bankloanapplication.model.LoanApplicant;
import com.suba.Bankloanapplication.model.TransactionStatus;
import com.suba.Bankloanapplication.repository.AccountDeletionRequestRepository;
import com.suba.Bankloanapplication.repository.CustomerRepository;
import com.suba.Bankloanapplication.repository.LoanApplicantRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Slf4j
public class AccountService {

    @Autowired
    private LoanApplicantRepository loanApplicantRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountDeletionRequestRepository accountDeletionRequestRepository;

    @Autowired
    private LoanApplicantService loanApplicantService;

    public String requestAccountDeletion(String accountNumber) throws AccountDeletionException {
        Customer customer = customerRepository.findByAccountNumber(accountNumber);
        if (customer == null) {
            log.error("Customer not found");
            throw new AccountDeletionException("Customer not found");
        }
        boolean pendingRequestExist = accountDeletionRequestRepository
                .findByCustomerAndStatusAndStatusNot(customer, TransactionStatus.PENDING.name(), "COMPLETED")
                .isPresent();
        if (pendingRequestExist) {
            log.error("Customer already has an active request");
            throw new AccountDeletionException("Customer already has an active request");
        }
        AccountDeletionRequest accountDeletionRequest = new AccountDeletionRequest();
        accountDeletionRequest.setCustomer(customer);
        accountDeletionRequest.setStatus(TransactionStatus.PENDING.name());
        accountDeletionRequest.setDate(LocalDateTime.now());
        accountDeletionRequestRepository.save(accountDeletionRequest);
        return "Account deletion request submitted successfully";
    }

    public String approveAccountDeletion(int requestId) throws AccountDeletionException {
        AccountDeletionRequest accountDeletionRequest = accountDeletionRequestRepository
                .findById(requestId).orElse(null);
        if (accountDeletionRequest == null) {
            log.error("Account deletion request not found");
            throw new AccountDeletionException("Account deletion request not found");
        }
        Customer customer = accountDeletionRequest.getCustomer();
        List<LoanApplicant> loans = loanApplicantRepository
                .findByCustomerAccountNumber(customer.getAccountNumber());
        boolean allLoansCompleted = loans.stream()
                .allMatch(loan -> "COMPLETED".equalsIgnoreCase(loan.getLoanStatus()) ||
                        "FAILED".equalsIgnoreCase(loan.getLoanStatus()));


        if (allLoansCompleted) {
            String notificationSubject = "Account Deletion Notification";
            String notificationReason = "Your account is scheduled for deletion.";
            loanApplicantService.sendEmail(customer.getEmail(), notificationSubject, notificationReason);
            loanApplicantRepository.deleteByCustomerAccountNumberIn(List.of(customer.getAccountNumber()));
            deleteRequest(customer);
            return "Account deletion approved and account deleted";
        } else {
            accountDeletionRequest.setStatus(TransactionStatus.FAILED.name());
            accountDeletionRequestRepository.save(accountDeletionRequest);
            log.warn("Account deletion request denied. There are some pending loans");
            throw new AccountDeletionException("Account deletion request denied. There are some pending loans");
        }
    }

    private void deleteRequest(Customer customer) {
        List<AccountDeletionRequest> deletionRequests = accountDeletionRequestRepository.findByCustomer(customer);
        accountDeletionRequestRepository.deleteAll(deletionRequests);
        customerRepository.deleteByAccountNumber(customer.getAccountNumber());
    }

    public List<AccountDeletionRequest> getAllPendingRequest() {
        return accountDeletionRequestRepository.findByStatus(TransactionStatus.PENDING.name());
    }
}
