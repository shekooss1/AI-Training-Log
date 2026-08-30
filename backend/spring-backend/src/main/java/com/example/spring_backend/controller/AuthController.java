package com.example.spring_backend.controller;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.example.spring_backend.DTOs.LoginRequest;
import com.example.spring_backend.service.JwtService;
import com.example.spring_backend.service.SwimmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    SwimmerService swimmerService;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authManager;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
       String email = loginRequest.getEmail();
       String password = loginRequest.getPassword();


        String token = verify(email,password);

       if(token==null){
         return   ResponseEntity.status(401).body("Invalid token");
       }

       return  ResponseEntity.ok(token);

    }


    public String verify(String email, String password) {
        try {
            Authentication authentication =
                    authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            return jwtService.generateToken(email);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
