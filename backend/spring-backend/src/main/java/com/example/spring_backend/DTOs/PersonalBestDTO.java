package com.example.spring_backend.DTOs;

import com.example.spring_backend.model.PersonalBest;
import com.example.spring_backend.model.Stroke;

public record PersonalBestDTO(Long id, double distance, double record, Stroke stroke) {
    public static PersonalBestDTO from(PersonalBest pb) {
        return new PersonalBestDTO(pb.getId(), pb.getDistance(), pb.getRecord(), pb.getStroke());
    }
    public PersonalBest toEntity() {
        return new PersonalBest(distance, record, stroke);
    }
}