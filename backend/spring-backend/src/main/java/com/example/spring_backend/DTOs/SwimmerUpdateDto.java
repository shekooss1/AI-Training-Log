package com.example.spring_backend.DTOs;

import com.example.spring_backend.model.Especiality;
import com.example.spring_backend.model.Sex;
import com.example.spring_backend.model.Stroke;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SwimmerUpdateDto(
        @DecimalMin("1")
        double age,

        Especiality especiality,

        @NotBlank(message = "User name cant be null")
        String name,

        @Email(message = "Email is not valid")
        @NotBlank
        String email,

        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
                message = "Password must be at least 8 characters and include an uppercase letter, lowercase letter, number, and special character"
        )
        String password,

        Sex sex,

        Stroke stroke) {
}