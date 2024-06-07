package com.suba.Bankloanapplication.controller;

import com.suba.Bankloanapplication.config.CustomerLoanDetails;
import com.suba.Bankloanapplication.service.CustomerLoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CustomerLoanController {

    @Autowired
    private CustomerLoanService customerLoanService;
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/customer/{accountNumber}/details")
    public ResponseEntity<CustomerLoanDetails> getCustomerLoanDetails(@PathVariable String accountNumber) {
        try {
            CustomerLoanDetails customerLoanDetails = customerLoanService.getCustomerLoanDetailsByAccountNumber(accountNumber);
            return ResponseEntity.ok(new CustomerLoanDetails(customerLoanDetails.getCustomer(),customerLoanDetails.getLoans()));
        } catch (EntityNotFoundException e) {
            CustomerLoanDetails customerLoanDetails=new CustomerLoanDetails();
            customerLoanDetails.setStatus("Failed");
            customerLoanDetails.setMessage("Customer not found");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customerLoanDetails);
        }
    }
}
