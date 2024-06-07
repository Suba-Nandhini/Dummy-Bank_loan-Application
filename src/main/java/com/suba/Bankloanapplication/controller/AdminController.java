package com.suba.Bankloanapplication.controller;

import com.suba.Bankloanapplication.dto.ReturnStatus;
import com.suba.Bankloanapplication.model.LoanApplicant;
import com.suba.Bankloanapplication.model.Transaction;
import com.suba.Bankloanapplication.model.TransactionStatus;
import com.suba.Bankloanapplication.repository.TransactionRepository;
import com.suba.Bankloanapplication.service.LoanApplicantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan/admin")

public class AdminController {
    @Autowired
    private LoanApplicantService service;
    @Autowired
    private LoanApplicantService loanApplicantService;
    @Autowired
    private TransactionRepository transactionRepository;

    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(value = "/view",produces = MediaType.APPLICATION_XML_VALUE)

    public List<LoanApplicant> view(){
        return service.findAll();
    }

    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/approvalView")
    public List<LoanApplicant> approvalView(){
        return loanApplicantService.findByeligibility("Eligible", TransactionStatus.PENDING.name(), 500);
    }




    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/approve/{id}")
    public ResponseEntity<ReturnStatus> approve(@PathVariable int id){
        String result="";
        try {
            result = service.approveLoan(id);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ReturnStatus("Failed",e.getMessage()));

        }

            return ResponseEntity.ok(new ReturnStatus("Success",result));

    }
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/transaction")
    public ResponseEntity<List<Transaction>> transaction(){
        List<Transaction> transactions=transactionRepository.findAll();
        if(transactions.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(transactions);
        }

    }







}
