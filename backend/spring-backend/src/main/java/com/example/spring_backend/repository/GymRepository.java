package com.example.spring_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_backend.DTOs.GymDTO;
import com.example.spring_backend.model.Gym;

public interface GymRepository extends JpaRepository<Gym, Long> {

    Optional<Gym> findByDailyLogId(Long dailyLogId);

}
