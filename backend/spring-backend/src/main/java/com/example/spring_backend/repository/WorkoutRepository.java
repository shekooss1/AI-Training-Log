package com.example.spring_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_backend.model.Workout;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

}
