package com.example.spring_backend.DTOs;

import com.example.spring_backend.model.Sleep;

public record SleepDTO(double hours , String notes) {
  public static SleepDTO from(Sleep entity) {
        return new SleepDTO(entity.getHours(), entity.getNotes());
    }

    public Sleep toEntity() {
        return new Sleep(hours,notes);
    }
}
