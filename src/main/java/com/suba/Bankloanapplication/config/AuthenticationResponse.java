package com.suba.Bankloanapplication.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String message;
    private String jwt;
}
