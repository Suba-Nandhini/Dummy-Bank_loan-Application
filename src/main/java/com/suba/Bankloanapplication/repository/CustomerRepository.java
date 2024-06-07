package com.suba.Bankloanapplication.repository;

import com.suba.Bankloanapplication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByAccountNumber(String accountNumber);
    void deleteByAccountNumber(String accountNumber);

}

