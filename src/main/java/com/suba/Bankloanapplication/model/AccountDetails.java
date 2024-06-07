package com.suba.Bankloanapplication.model;

import lombok.Data;
import org.springframework.web.bind.annotation.RestController;


@Data
public class AccountDetails {
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private double amount;

}
