package com.example.spring_backend.DTOs;

import com.example.spring_backend.model.Set;

public record SetDTO(int reps, int distance, int rest, String target, double intenisty  ) {
  public static SetDTO from(Set entity) {
        return new SetDTO(entity.getReps(), entity.getDistance(), entity.getRest(), entity.getTarget(), entity.getIntenisty());
    }

    public Set toEntity() {
        return new Set(reps, distance, rest, target, intenisty);
    }
}
