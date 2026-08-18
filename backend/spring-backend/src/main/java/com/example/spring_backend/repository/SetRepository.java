package com.example.spring_backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_backend.model.Set;

public interface SetRepository extends JpaRepository<Set, Long> {

}
