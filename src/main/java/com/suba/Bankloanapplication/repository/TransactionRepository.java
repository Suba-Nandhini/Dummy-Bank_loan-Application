package com.suba.Bankloanapplication.repository;

import com.suba.Bankloanapplication.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, Integer> {
    public List<Transaction> findAll();
    public Transaction findByReceiverAccountNumber(String accountNumber);
}