package com.suba.Bankloanapplication.model;


import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@Document(collection="loan_approval_transactions")
public class LoanApprovalTransaction {
    @Id
    private String id;
    private int customerId;
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private Date transactionTime;
    private double transactionAmount;


}
