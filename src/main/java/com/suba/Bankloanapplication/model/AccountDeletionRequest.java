package com.suba.Bankloanapplication.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "account_deletion_request")
public class AccountDeletionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private String status;
    private LocalDateTime date;
}