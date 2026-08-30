package com.example.spring_backend.repository;

import org.springframework.beans.PropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_backend.model.DailyLog;

import java.util.List;
import java.util.Optional;

public interface DailyLogRepository extends JpaRepository<DailyLog, Long> {
    Optional<DailyLog> findByIdAndSwimmer_Email(Long id, String email);

    List<DailyLog> findAllBySwimmerEmail(String email);
}
