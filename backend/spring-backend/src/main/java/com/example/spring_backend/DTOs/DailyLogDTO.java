package com.example.spring_backend.DTOs;

import java.time.LocalDate;

import com.example.spring_backend.model.DailyLog;
import com.example.spring_backend.model.SessionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record DailyLogDTO (Long id,
                           @NotNull
                           @PastOrPresent(message = "date cannot be future")
                           LocalDate date ,

                           @NotNull
                           SessionType sessionType ,

                           String notes) {
  
    public static DailyLogDTO from(DailyLog entity) {
        return new DailyLogDTO(entity.getId(),entity.getDate(), entity.getSessionType(), entity.getNotes());
    }

    public DailyLog toEntity() {
        return new DailyLog(date ,sessionType, notes);
    }
}
