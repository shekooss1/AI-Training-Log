package com.example.spring_backend.DTOs;

import com.example.spring_backend.model.Gym;

public record GymDTO(Long id,String exercise, String notes, double sets, double reps, double weight) {
 
    public static GymDTO from(Gym entity) {
        return new GymDTO(entity.getId(),entity.getExercise(), entity.getNotes(), entity.getSets(), entity.getReps(), entity.getWeight());
    }

    public Gym toEntity() {
        return new Gym(exercise, notes, sets, reps, weight);
    }
}
