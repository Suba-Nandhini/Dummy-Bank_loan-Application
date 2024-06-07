package com.suba.Bankloanapplication.repository;

import com.suba.Bankloanapplication.model.AccountDeletionRequest;
import com.suba.Bankloanapplication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountDeletionRequestRepository extends JpaRepository<AccountDeletionRequest, Integer> {

    Optional<AccountDeletionRequest>findByCustomerAndStatusAndStatusNot(Customer customer, String status, String notStatus);

    List<AccountDeletionRequest> findByStatus(String status);

    List<AccountDeletionRequest> findByCustomer(Customer customer);
}