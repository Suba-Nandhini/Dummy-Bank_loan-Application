package com.suba.Bankloanapplication.controller;

import com.suba.Bankloanapplication.dto.ReturnStatus;
import com.suba.Bankloanapplication.service.LoanApplicantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/loan")

public class LoanApplicantController {
    @Autowired
    private LoanApplicantService loanApplicantService;
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/add")
    public ResponseEntity<ReturnStatus> addLoanApplicant(@RequestBody it.bankapp.LoanApplicantdto loanApplicant) {
        String msg="";
        try {
            msg = loanApplicantService.AddApplication(loanApplicant);
        }
        catch(Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ReturnStatus("Failed",e.getMessage()) );


        }

            return ResponseEntity.status(HttpStatus.OK).body(new ReturnStatus("Success",msg));



    }
}
