package com.example.spring_backend.DTOs;

import com.example.spring_backend.model.Sleep;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

public record SleepDTO(Long id,
                       @NotBlank
                       @DecimalMin("1")
                       double hours ,

                       @NotBlank
                       String notes) {
  public static SleepDTO from(Sleep entity) {
        return new SleepDTO(entity.getId(),entity.getHours(), entity.getNotes());
    }

    public Sleep toEntity() {
        return new Sleep(hours,notes);
    }
}
