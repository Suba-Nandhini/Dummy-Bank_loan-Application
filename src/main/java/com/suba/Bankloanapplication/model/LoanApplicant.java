package com.suba.Bankloanapplication.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "loan")
@JacksonXmlRootElement(localName = "LoanApplication")
public class LoanApplicant {
    @JacksonXmlProperty(localName = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JacksonXmlProperty(localName = "customerAccountNumber")
    private String customerAccountNumber;
    @JacksonXmlProperty(localName = "loanType")
    private String loanType;
    @JacksonXmlProperty(localName = "customerEmail")
    private String customerEmail;

    @JacksonXmlProperty(localName = "loanStatus")
    private String loanStatus=TransactionStatus.PENDING.name();
    @JacksonXmlProperty(localName = "loanAmount")
    private double loanAmount;
    @JacksonXmlProperty(localName = "rateOfInterest")
    private float rateOfInterest;
    @JacksonXmlProperty(localName ="emiAmt")
    private double emiAmt;
    @JacksonXmlProperty(localName = "existing_emi_amt")
    private double existing_emi_amt=0;
    @JacksonXmlProperty(localName = "tenure")
    private int tenure;
    @JacksonXmlProperty(localName = "netSalary")
    private long netSalary;
    @JacksonXmlProperty(localName = "eligiblityStatus")
    private String eligiblityStatus="Not Eligible";
    @JacksonXmlProperty(localName = "cibilScore")
    private int cibilScore;





}
