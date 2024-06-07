package com.suba.Bankloanapplication.config;

import com.suba.Bankloanapplication.model.Customer;
import com.suba.Bankloanapplication.model.LoanApplicant;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@Data
public class CustomerLoanDetails {
    private Customer customer;
    private List<LoanApplicant> loans;
    private String status;
    private String message;
    public CustomerLoanDetails(Customer customer, List<LoanApplicant> loans) {
        this.customer = customer;
        this.loans = loans;
    }
}