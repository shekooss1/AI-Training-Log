package com.example.spring_backend.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class LoginRequest {
    @NotBlank
    @Email(message = "Invalid email address")
    @NotNull
    private String email;

    @NotBlank
    @NotNull
    @Length(min = 8)
    private String password;

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

}
