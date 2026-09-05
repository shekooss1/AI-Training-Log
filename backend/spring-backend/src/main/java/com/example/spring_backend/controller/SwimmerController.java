package com.example.spring_backend.controller;

import java.util.List;

import com.example.spring_backend.DTOs.SwimmerUpdateDto;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import com.example.spring_backend.DTOs.SwimmerResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_backend.DTOs.SwimmerDTO;
import com.example.spring_backend.service.SwimmerService;

@RestController
@RequestMapping("/api/swimmers")
public class SwimmerController {
    SwimmerService ss;

    public SwimmerController(SwimmerService ss) {
        this.ss = ss;
    }


    @PostMapping
    public ResponseEntity<SwimmerResponseDTO> createSwimmer(@RequestBody @Valid SwimmerDTO dto) {
        SwimmerResponseDTO created = ss.createSwimmer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SwimmerResponseDTO> getById(@PathVariable Long id, Authentication authentication) {
        String email = authentication.getName();
        return ss.getSwimmerById(id, email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SwimmerResponseDTO> updateSwimmer(@PathVariable Long id, @RequestBody @Valid SwimmerUpdateDto dto, Authentication authentication) {
        String email = authentication.getName();
        return ss.updateSwimmer(id, dto, email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSwimmer(@PathVariable Long id, Authentication authentication) {
        String email = authentication.getName();
        ss.deleteSwimmer(id, email);
        return ResponseEntity.noContent().build();
}
}
 


