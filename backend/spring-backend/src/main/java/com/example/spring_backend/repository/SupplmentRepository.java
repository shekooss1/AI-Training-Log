package com.example.spring_backend.repository;

import java.util.List;
import java.util.Optional;

import com.example.spring_backend.model.Sleep;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_backend.model.Supplment;

public interface SupplmentRepository extends JpaRepository<Supplment, Long> {

    List<Supplment> findAllByDailyLog_IdAndDailyLog_Swimmer_Email(Long dailyLogId, String email);
    Optional<Supplment> findByIdAndDailyLog_Swimmer_Email(Long id, String email);

}
