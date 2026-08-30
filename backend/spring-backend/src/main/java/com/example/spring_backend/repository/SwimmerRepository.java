package com.example.spring_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_backend.DTOs.SwimmerDTO;
import com.example.spring_backend.model.Swimmer;

public interface SwimmerRepository extends JpaRepository<Swimmer, Long>{

    Optional<Swimmer> findByEmail(String email);
    Optional<Swimmer> findByIdAndEmail(Long id, String email);

}
