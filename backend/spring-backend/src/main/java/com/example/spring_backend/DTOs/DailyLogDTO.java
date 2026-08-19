package com.example.spring_backend.DTOs;

import java.time.LocalDate;

import com.example.spring_backend.model.DailyLog;
import com.example.spring_backend.model.SessionType;

public record DailyLogDTO (Long id,LocalDate date , SessionType sessionType ,String notes) {
  
    public static DailyLogDTO from(DailyLog entity) {
        return new DailyLogDTO(entity.getId(),entity.getDate(), entity.getSessionType(), entity.getNotes());
    }

    public DailyLog toEntity() {
        return new DailyLog(date ,sessionType, notes);
    }
}
