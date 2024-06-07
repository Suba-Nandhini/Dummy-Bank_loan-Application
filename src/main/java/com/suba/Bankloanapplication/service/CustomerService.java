package com.suba.Bankloanapplication.service;

import com.suba.Bankloanapplication.exception.CustomerException;
import com.suba.Bankloanapplication.mapper.CustomerMapper;
import com.suba.Bankloanapplication.model.*;
import com.suba.Bankloanapplication.repository.CustomerRepository;
import com.suba.Bankloanapplication.repository.LoanApplicantRepository;
import com.suba.Bankloanapplication.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class CustomerService {
    @Autowired
    private  CustomerRepository customerRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private LoanApplicantService loanApplicantService;
    @Autowired
    private LoanApplicantRepository loanApplicantRepository;

    public String getCustomerTypeByAccountNumber(String accountNumber) {
        Customer customer = customerRepository.findByAccountNumber(accountNumber);
        if (customer != null) {
            return customer.getCustomerType();

        }
        return null;
    }
    public String addCustomer(it.bankapp.Customerdto customer) throws CustomerException {
        LoanApplicant loanApplicant = new LoanApplicant();
        if(!isValidPan(customer.getPanCard())){
            log.error("Pan card number is incorrect");
            throw new CustomerException("Pan card number is incorrect");
        }
        if(!isValidPhoneNumber(customer.getPhoneNumber())){
            log.error("Invalid Phone Number");
            throw new CustomerException("Invalid Phone Number");
        }
        if (!isValidAccountNumber(customer.getAccountNumber())) {
            log.error("Invalid account number");
            throw new CustomerException("Invalid account number");
        }

        customerRepository.save(CustomerMapper.customerDtoToCustomer(customer));
        return "Customer Added Successfully";
    }

    public static boolean isValidPan(String pan) {
        // Regular expression pattern for PAN validation
        String panPattern = "[A-Z]{3}[A-Za-z][A-Z]\\d{4}[A-Z]";

        // Compile the pattern
        Pattern pattern = Pattern.compile(panPattern);

        // Match the input PAN against the pattern
        Matcher matcher = pattern.matcher(pan);

        // Extract components of the PAN
        String firstThreeChars = pan.substring(0, 3);
        char fourthChar = pan.charAt(3);
        char fifthChar = pan.charAt(4);
        String nextFourDigits = pan.substring(5, 9);
        char tenthChar = pan.charAt(9);

        // Checking if the input PAN matches the pattern
        if (matcher.matches() && firstThreeChars.matches("[A-Z]{3}") && Character.isLetter(fourthChar) && Character.isLetter(fifthChar) && nextFourDigits.matches("\\d{4}") && Character.isLetter(tenthChar)) {
                                return true;

        }
        // PAN format is not valid
        return false;
    }
    boolean isValidPhoneNumber(String phoneNumber) {
        String accountNumberRegex = "\\d{10}";
        return Pattern.matches(accountNumberRegex, phoneNumber);
    }

    boolean isValidAccountNumber(String customerAccountNumber) {
        String accountNumberRegex="\\d{11,16}";
        return Pattern.matches(accountNumberRegex, customerAccountNumber);
    }

    public boolean creditAccount(AccountDetails accountDetails){
        try{
            Customer customer=customerRepository.findByAccountNumber(accountDetails.getReceiverAccountNumber());
            if(customer!=null){
                double newBalance=customer.getBalance()+accountDetails.getAmount();
                customer.setBalance(newBalance);
                Transaction transaction=new Transaction();
                transaction.setCustomerId(customer.getId());
                transaction.setSenderAccountNumber(accountDetails.getSenderAccountNumber());
                transaction.setReceiverAccountNumber(accountDetails.getReceiverAccountNumber());
                transaction.setTransactionTime(LocalDateTime.now());
                transaction.setAmount(accountDetails.getAmount());
                transaction.setTransactionType(TransactionType.CREDIT);
                customerRepository.save(customer);
                transactionRepository.save(transaction);
                Customer loanApplicant=customerRepository.findByAccountNumber(accountDetails.getReceiverAccountNumber());
                loanApplicantService.sendEmail(customer.getEmail(),"Loan Credited Notification","Your a/c no.  "+maskedAccount(accountDetails.getReceiverAccountNumber())+" is  credited for Rs."+accountDetails.getAmount()+" on "+transaction.getTransactionTime()+" and a/c " +maskedAccount(transaction.getSenderAccountNumber())+" credited .");
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }
    public String maskedAccount(String accountNumber) {
        if (accountNumber != null && accountNumber.length() >= 11 && accountNumber.length() <= 16) {

            String lastFourDigits = accountNumber.substring(accountNumber.length() - 4);

            String masked = accountNumber.substring(0, accountNumber.length() - 4).replaceAll(".", "X");

            return masked + lastFourDigits;
        } else {

            return accountNumber;
        }
    }
    @Transactional
    public String updateCustomer(it.bankapp.Customerdto customer)throws CustomerException {
        Customer existingCustomer=customerRepository.findByAccountNumber(customer.getAccountNumber());
        if(existingCustomer!=null){
            existingCustomer.setBalance(customer.getBalance());
            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
            existingCustomer.setAccountNumber(customer.getAccountNumber());
            existingCustomer.setNetSalary(customer.getNetSalary());
            existingCustomer.setPanCard(customer.getPanCard());
            existingCustomer.setResidentYears(customer.getResidentYears());
            existingCustomer.setWorkingYears(customer.getWorkingYears());
            existingCustomer.setHavingAccount(customer.getHavingAccount());
            customerRepository.save(existingCustomer);return "Customer Details Updated Successfully";

        }
        else{
            log.error("Customer not found for this account number");
            throw new CustomerException("Customer not found for this account number");
        }
    }
}
