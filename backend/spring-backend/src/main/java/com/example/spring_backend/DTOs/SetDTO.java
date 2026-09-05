package com.example.spring_backend.DTOs;

import com.example.spring_backend.model.Set;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record SetDTO(Long id ,
                     @NotBlank
                     @Min(1)
                     int reps,
                     @NotBlank
                     @Min(10)
                     int distance,
                     @NotBlank
                     @Min(1)
                     int rest,

                     @NotBlank
                     String target,

                     @NotBlank
                     @DecimalMin("60")
                     double intenisty  ) {
  public static SetDTO from(Set entity) {
        return new SetDTO(entity.getId(),entity.getReps(), entity.getDistance(), entity.getRest(), entity.getTarget(), entity.getIntenisty());
    }

    public Set toEntity() {
        return new Set(reps, distance, rest, target, intenisty);
    }
}
