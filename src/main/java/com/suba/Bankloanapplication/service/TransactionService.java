package com.suba.Bankloanapplication.service;

import com.suba.Bankloanapplication.model.Transaction;
import com.suba.Bankloanapplication.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    public void recordTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
