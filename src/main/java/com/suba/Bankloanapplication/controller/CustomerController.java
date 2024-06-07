package com.suba.Bankloanapplication.controller;

import com.suba.Bankloanapplication.dto.ReturnStatus;
import com.suba.Bankloanapplication.exception.AccountDeletionException;
import com.suba.Bankloanapplication.exception.CustomerException;
import com.suba.Bankloanapplication.model.AccountDetails;
import com.suba.Bankloanapplication.service.AccountService;
import com.suba.Bankloanapplication.service.CustomerService;
import com.suba.Bankloanapplication.service.LoanApplicantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private LoanApplicantService loanApplicantService;
    String result="Failed";
    String success = "Success";
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/addCustomer")
    public ResponseEntity<ReturnStatus> addCustomer(@RequestBody it.bankapp.Customerdto customer) {
        String message="";

        try {
            message= customerService.addCustomer(customer);
        }
        catch (DataIntegrityViolationException e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ReturnStatus(success,"Customer already exists"));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ReturnStatus(result,"Customer could not be added"));
        }
        if(message.equals("Customer Added Successfully")){


            return ResponseEntity.ok(new ReturnStatus(success,message));
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ReturnStatus(result,message));
        }

    }
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/credit")
    public ResponseEntity<ReturnStatus> creditAccount(@RequestBody AccountDetails loanApprovalTransaction){

        if(!loanApplicantService.isValidAccountNumber(loanApprovalTransaction.getReceiverAccountNumber()) || !loanApplicantService.isValidAccountNumber(loanApprovalTransaction.getSenderAccountNumber())){
            return ResponseEntity.badRequest().body(new ReturnStatus(result,"Invalid account number"));
        }
        boolean credited=customerService.creditAccount(loanApprovalTransaction);
        if(credited){

            return ResponseEntity.ok(new ReturnStatus(success,"Amount credited Successfully"));
        }
        else{
            return ResponseEntity.badRequest().body(new ReturnStatus(result,"Failed to credit amount to the account."));
        }
    }
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/updateCustomer")
    public ResponseEntity<ReturnStatus> updateCustomer(@RequestBody it.bankapp.Customerdto customer){

            String msg="";
            try {
                 msg = customerService.updateCustomer(customer);
            }
            catch(CustomerException e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ReturnStatus(result,e.getMessage()));
            }
            return ResponseEntity.ok(new ReturnStatus(success,msg));

    }
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/request-deletion")
    public ResponseEntity<ReturnStatus> requestDeletion(@RequestParam String accountNumber){
        String msg="";
        try {
            msg = accountService.requestAccountDeletion(accountNumber);
       }
        catch(AccountDeletionException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ReturnStatus(result,e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ReturnStatus(success,msg));

    }
}
