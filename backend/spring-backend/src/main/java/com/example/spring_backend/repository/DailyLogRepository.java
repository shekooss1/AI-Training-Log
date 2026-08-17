package com.example.spring_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_backend.model.DailyLog;

public interface DailyLogRepository extends JpaRepository<DailyLog, Long> {

}
