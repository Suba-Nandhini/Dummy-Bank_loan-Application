package com.suba.Bankloanapplication.service;

import com.suba.Bankloanapplication.config.CustomerLoanDetails;
import com.suba.Bankloanapplication.model.Customer;
import com.suba.Bankloanapplication.model.LoanApplicant;
import com.suba.Bankloanapplication.repository.CustomerRepository;
import com.suba.Bankloanapplication.repository.LoanApplicantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerLoanService {

    private final  LoanApplicantRepository loanApplicantRepository;
    private final CustomerRepository customerRepository;

    CustomerLoanService(CustomerRepository customerRepository,LoanApplicantRepository loanApplicantRepository) {
        this.customerRepository = customerRepository;
        this.loanApplicantRepository = loanApplicantRepository;
    }


    public CustomerLoanDetails getCustomerLoanDetailsByAccountNumber(String accountNumber) {
        // Retrieve customer details

        Customer customer = customerRepository.findByAccountNumber(accountNumber);

        if (customer == null) {
            throw new EntityNotFoundException("Customer not found");
        }

        // Retrieve loan details for the customer
        List<LoanApplicant> loans = loanApplicantRepository.findByCustomerAccountNumber(accountNumber);

        // Return a custom object containing both customer and loan details
        return new CustomerLoanDetails(customer, loans);
    }
}

