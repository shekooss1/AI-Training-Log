package com.example.spring_backend.DTOs;
import com.example.spring_backend.model.Especiality;
import com.example.spring_backend.model.Sex;
import com.example.spring_backend.model.Stroke;
import com.example.spring_backend.model.Swimmer;

public record  SwimmerDTO(double age, Especiality especiality, String name, Sex sex, Stroke stroke) {
  public static SwimmerDTO from(Swimmer entity) {
        return new SwimmerDTO(entity.getAge(), entity.getEspeciality(), entity.getName(), entity.getSex(), entity.getStroke());
    }

    public Swimmer toEntity() {
        return new Swimmer(age,especiality,name,sex,stroke);
    }
}
