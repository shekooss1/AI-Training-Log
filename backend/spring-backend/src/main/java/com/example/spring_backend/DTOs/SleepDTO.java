package com.example.spring_backend.DTOs;

import com.example.spring_backend.model.Sleep;

public record SleepDTO(Long id, double hours , String notes) {
  public static SleepDTO from(Sleep entity) {
        return new SleepDTO(entity.getId(),entity.getHours(), entity.getNotes());
    }

    public Sleep toEntity() {
        return new Sleep(hours,notes);
    }
}
