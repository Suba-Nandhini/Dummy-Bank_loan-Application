package com.suba.Bankloanapplication.config;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
