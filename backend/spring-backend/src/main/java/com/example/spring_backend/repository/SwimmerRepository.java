package com.example.spring_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_backend.model.Swimmer;

public interface SwimmerRepository extends JpaRepository<Swimmer, Long>{

}
