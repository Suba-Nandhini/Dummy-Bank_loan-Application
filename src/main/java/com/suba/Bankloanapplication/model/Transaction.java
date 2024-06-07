package com.suba.Bankloanapplication.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document(collection = "Transaction")
public class Transaction {

    private int customerId; // ID of the customer associated with the transaction
    private String senderAccountNumber; // Account number of the sender
    private String receiverAccountNumber; // Account number of the receiver
    private LocalDateTime transactionTime; // Timestamp of the transaction
    private double amount; // Amount of money involved in the transaction
    private TransactionType transactionType; // Type of transaction (e.g., credit, debit, loan disbursement, etc.)


}
