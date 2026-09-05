package com.example.spring_backend.DTOs;

import com.example.spring_backend.model.Gym;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GymDTO(Long id,
                     @NotBlank(message = "Must enter an exercise")
                     @NotNull
                     String exercise,
                     String notes,

                     @DecimalMin("1")
                     @NotNull
                     double sets,

                     @NotNull
                     @DecimalMin("1")
                     double reps,

                     @NotNull
                     @DecimalMin("1")
                     double weight) {
 
    public static GymDTO from(Gym entity) {
        return new GymDTO(entity.getId(),entity.getExercise(), entity.getNotes(), entity.getSets(), entity.getReps(), entity.getWeight());
    }

    public Gym toEntity() {
        return new Gym(exercise, notes, sets, reps, weight);
    }
}
