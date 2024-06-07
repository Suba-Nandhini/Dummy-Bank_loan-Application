package com.suba.Bankloanapplication.service;

import com.suba.Bankloanapplication.config.AuthenticationRequest;
import com.suba.Bankloanapplication.config.AuthenticationResponse;
import com.suba.Bankloanapplication.config.JwtService;
import com.suba.Bankloanapplication.model.Role;
import com.suba.Bankloanapplication.model.User;
import com.suba.Bankloanapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service

public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    private boolean adminRegistered ;
    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;


    }
    public String assignRoles(String userName, String password) {
        if(userRepository.findByUsername(userName)!=null){
            return new RuntimeException("Admin name already exists").getMessage();
        }
        else{

            User user = new User();
            user.setUsername(userName);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(Role.ADMIN);
            userRepository.save(user);
            return "Role assigned successfully";
        }


    }
    public String Register(AuthenticationRequest user, String role) {
        try {
            // Validate input
            if (user == null || user.getUsername() == null || user.getPassword() == null) {
                return "Invalid input";
            }

            // Check if user already exists
            if (userRepository.findByUsername(user.getUsername()) != null) {
                return "Username already exists";
            }
            Role r = switch (role) {
                case "C" -> Role.CUSTOMER;
                case "SA" -> Role.SUPER_ADMIN;
                case "A" -> Role.ADMIN;
                default -> null;
            };
            User newUser = new User(user.getUsername(), passwordEncoder.encode(user.getPassword()),r);
            userRepository.save(newUser);

            return "Registration successful";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred during registration";
        }
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        try{
            UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),authenticationRequest.getPassword()
            );
            authenticationManager.authenticate(authToken);
            User user =userRepository.findByUsername(authenticationRequest.getUsername());
            if(user==null){
                throw new UsernameNotFoundException("Username not found");
            }
            String jwt=jwtService.generateToken(user,generateExtraClaims(user));
            return new AuthenticationResponse("You have logged in successfully",jwt);
        }
        catch(AuthenticationException e){
            e.printStackTrace();
            throw new BadCredentialsException("Invalid User name or password");
        }
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username", user.getUsername());
        extraClaims.put("role", user.getRole().name());
        return extraClaims;
    }


}
