package com.example.spring_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_backend.model.Supplment;

public interface SupplmentRepository extends JpaRepository<Supplment, Long> {

    Optional<Supplment> findByDailyLogId(Long dailyLogId);

}
