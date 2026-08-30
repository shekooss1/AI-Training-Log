package com.example.spring_backend.repository;


import java.util.List;
import java.util.Optional;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_backend.model.Set;

public interface SetRepository extends JpaRepository<Set, Long> {
    List<Set> findAllByDailyLog_IdAndDailyLog_Swimmer_Email(Long dailyLogId, String email);
    Optional<Set> findByIdAndDailyLog_Swimmer_Email(Long id, String email);

}
