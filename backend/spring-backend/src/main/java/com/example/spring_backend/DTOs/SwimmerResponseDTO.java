package com.example.spring_backend.DTOs;

import com.example.spring_backend.model.Especiality;
import com.example.spring_backend.model.Sex;
import com.example.spring_backend.model.Stroke;
import com.example.spring_backend.model.Swimmer;

public record SwimmerResponseDTO(Long id , double age, Especiality especiality, String name, String email, Sex sex, Stroke stroke){
    public static SwimmerResponseDTO from(Swimmer entity) {
        return new SwimmerResponseDTO(entity.getId(),entity.getAge(), entity.getEspeciality(), entity.getName(), entity.getEmail(), entity.getSex(), entity.getStroke());
    }

}
