package com.example.spring_backend.repository;

import java.util.List;
import java.util.Optional;

import com.example.spring_backend.model.Set;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_backend.model.Sleep;

public interface SleepRepository extends JpaRepository<Sleep, Long> {

    List<Sleep> findAllByDailyLog_IdAndDailyLog_Swimmer_Email(Long dailyLogId, String email);
    Optional<Sleep> findByIdAndDailyLog_Swimmer_Email(Long id, String email);
}
