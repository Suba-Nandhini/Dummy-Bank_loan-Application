package com.suba.Bankloanapplication.controller;

import com.suba.Bankloanapplication.config.AuthenticationRequest;
import com.suba.Bankloanapplication.config.AuthenticationResponse;
import com.suba.Bankloanapplication.config.RegistrationResponse;
import com.suba.Bankloanapplication.dto.ReturnStatus;
import com.suba.Bankloanapplication.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Hidden;
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
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;


    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Hidden
    @PostMapping("/registerAdmin")
    public ResponseEntity<ReturnStatus> registerAdmin(@RequestBody AuthenticationRequest userDetails) {
        String message= authenticationService.Register(userDetails,"SA");
        return ResponseEntity.ok(new ReturnStatus("Success",message));
    }
    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody AuthenticationRequest request) {
        try {
            String message = authenticationService.Register(request, "C");
            RegistrationResponse response = new RegistrationResponse(message);
            HttpStatus status = message.equals("Registration successful") ? HttpStatus.OK : HttpStatus.CONFLICT;
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RegistrationResponse("Error occurred during registration"));
        }
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        AuthenticationResponse authResponse=authenticationService.login(request);
        if(authResponse.getMessage().equals("Invalid User name or password")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authResponse);
        }

        return ResponseEntity.ok(authResponse);
    }

    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/assign-role")
    public ResponseEntity<String> assignRole(@RequestBody AuthenticationRequest request) {
        String result = authenticationService.Register(request,"A");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
