package com.example.spring_backend.DTOs;
import com.example.spring_backend.model.Especiality;
import com.example.spring_backend.model.Sex;
import com.example.spring_backend.model.Stroke;
import com.example.spring_backend.model.Swimmer;
 
public record  SwimmerDTO(Long id ,double age, Especiality especiality, String name, String email, String password, Sex sex, Stroke stroke) {

    public Swimmer toEntity() {
        return new Swimmer(age,especiality,name,email,password,sex,stroke);
    }
}
 