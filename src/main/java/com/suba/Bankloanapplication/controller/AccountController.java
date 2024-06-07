package com.suba.Bankloanapplication.controller;

import com.suba.Bankloanapplication.dto.ReturnStatus;
import com.suba.Bankloanapplication.exception.AccountDeletionException;
import com.suba.Bankloanapplication.model.AccountDeletionRequest;
import com.suba.Bankloanapplication.repository.AccountDeletionRequestRepository;
import com.suba.Bankloanapplication.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountDeletionRequestRepository accountDeletionRequestRepository;
    String result="Failed";
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/request-deletion")
    public ResponseEntity<ReturnStatus> requestAccountDeletion(@RequestParam String accountNumber) {
        String msg=null;
       try {
            msg = accountService.requestAccountDeletion(accountNumber);
       }
       catch(AccountDeletionException e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ReturnStatus("Failed", e.getMessage()));
       }
            return ResponseEntity.status(HttpStatus.OK).body(new ReturnStatus("Success",msg));

    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/approve-deletion/{requestId}")
    public ResponseEntity<ReturnStatus> approveAccountDeletion(@PathVariable int requestId) {
        String msg = null;
        try {
            msg = accountService.approveAccountDeletion(requestId);
        }catch (AccountDeletionException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ReturnStatus("Failed", e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ReturnStatus("Success",msg));
    }


    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/pending-deletion-request")
    public List<AccountDeletionRequest> getAllPendingRequests() {
        return accountService.getAllPendingRequest();
    }
}
