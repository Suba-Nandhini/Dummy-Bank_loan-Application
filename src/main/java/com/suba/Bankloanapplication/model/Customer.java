package com.suba.Bankloanapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Customer")
public class Customer {
    public static java.lang.Class<? extends Customer> Class;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    private String email;
    private long netSalary;
    private String phoneNumber;
    @Column(unique = true)
    private String panCard;
    @Column(unique = true)
    private String accountNumber;
    private int residentYears;
    private int workingYears;
    private boolean havingAccount;
    private String customerType;
    private double balance=0.0;
    private TransactionStatus loanStatus;
    private double debit=0.0;



}
