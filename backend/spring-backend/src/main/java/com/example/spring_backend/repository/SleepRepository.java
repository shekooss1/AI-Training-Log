package com.example.spring_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_backend.model.Sleep;

public interface SleepRepository extends JpaRepository<Sleep, Long> {

    Optional<Sleep> findByDailyLogId(Long dailyLogId);

}
