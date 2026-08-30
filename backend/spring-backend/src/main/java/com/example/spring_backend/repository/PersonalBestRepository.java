package com.example.spring_backend.repository;

import java.util.List;
import java.util.Optional;

import com.example.spring_backend.model.Sleep;
import org.springframework.beans.PropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_backend.model.PersonalBest;

public interface PersonalBestRepository extends JpaRepository<PersonalBest, Long>{

    Optional<PersonalBest> findByIdAndSwimmer_Email(Long id, String email);

    List<PersonalBest> findAllBySwimmer_Email(String email);
}
