package com.example.spring_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_backend.model.Gym;

public interface GymRepository extends JpaRepository<Gym, Long> {

}
