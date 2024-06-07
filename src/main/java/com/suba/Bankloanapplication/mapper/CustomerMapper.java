package com.suba.Bankloanapplication.mapper;


import com.suba.Bankloanapplication.model.Customer;

public class CustomerMapper {

    public static Customer customerDtoToCustomer(it.bankapp.Customerdto dto){
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setAccountNumber(dto.getAccountNumber());
        customer.setBalance(dto.getBalance());
        customer.setPanCard(dto.getPanCard());
        customer.setNetSalary(dto.getNetSalary());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setResidentYears(dto.getResidentYears());
        customer.setWorkingYears(dto.getWorkingYears());
        customer.setHavingAccount(dto.getHavingAccount());
        if(dto.getHavingAccount() && dto.getNetSalary()>=50000 && dto.getWorkingYears()>=2 && customer.getResidentYears()>=2) {
            customer.setCustomerType("Privileged");
        }
        else{
            customer.setCustomerType("Non-Privileged");
        }
        return customer;
    }

}