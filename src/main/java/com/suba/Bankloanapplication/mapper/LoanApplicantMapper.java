package com.suba.Bankloanapplication.mapper;

import com.suba.Bankloanapplication.model.Customer;
import com.suba.Bankloanapplication.model.LoanApplicant;
import com.suba.Bankloanapplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoanApplicantMapper {
    @Autowired
    private static CustomerRepository customerRepository;
    public static LoanApplicant LoanApplicantdtotoLoanApplicant(it.bankapp.LoanApplicantdto dto, Customer customer){
        LoanApplicant loanApplicant=new LoanApplicant();
        loanApplicant.setCustomerAccountNumber(dto.getCustomerAccountNumber());
        loanApplicant.setLoanType(dto.getLoanType());
        loanApplicant.setCustomerEmail(customer.getEmail());
        loanApplicant.setLoanAmount(dto.getLoanAmount());
        loanApplicant.setExisting_emi_amt(dto.getExistingEmiAmt());
        loanApplicant.setTenure(dto.getTenure());
        loanApplicant.setNetSalary(customer.getNetSalary());
        return loanApplicant;

    }
}
