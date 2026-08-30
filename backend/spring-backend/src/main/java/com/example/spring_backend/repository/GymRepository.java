package com.example.spring_backend.repository;

import java.util.List;
import java.util.Optional;

import com.example.spring_backend.model.Sleep;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_backend.model.Gym;

public interface GymRepository extends JpaRepository<Gym, Long> {

    List<Gym> findAllByDailyLog_IdAndDailyLog_Swimmer_Email(Long dailyLogId, String email);
    Optional<Gym> findByIdAndDailyLog_Swimmer_Email(Long id, String email);
}
