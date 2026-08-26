package com.example.spring_backend.controller;
 
import java.util.List;

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
  public SwimmerResponseDTO register(@RequestBody SwimmerDTO swimmerDTO) {
    return ss.createSwimmer(swimmerDTO);
  }
  
  
  @PostMapping
  public ResponseEntity<SwimmerResponseDTO> createSwimmer(@RequestBody SwimmerDTO dto) {
    try {
      SwimmerResponseDTO created = ss.createSwimmer(dto);
      return ResponseEntity.status(HttpStatus.CREATED).body(created);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
 
  @GetMapping
  public ResponseEntity<List<SwimmerResponseDTO>> getAll() {
    try {
      List<SwimmerResponseDTO> list = ss.getAllSwimmers();
      return ResponseEntity.status(HttpStatus.OK).body(list);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
 
  @GetMapping("/{id}")
  public ResponseEntity<SwimmerResponseDTO> getById(@PathVariable Long id) {
    try {
      return ss.getSwimmerById(id)
          .map(ResponseEntity::ok)
          .orElse(ResponseEntity.notFound().build());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
 
  @PutMapping("/{id}")
  public ResponseEntity<SwimmerResponseDTO> updateSwimmer(@PathVariable Long id, @RequestBody SwimmerDTO dto) {
    try {
      return ss.updateSwimmer(id, dto)
          .map(ResponseEntity::ok)
          .orElse(ResponseEntity.notFound().build());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
 
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteSwimmer(@PathVariable Long id) {
    try {
      boolean deleted = ss.deleteSwimmer(id);
      if (deleted) {
        return ResponseEntity.noContent().build();
      }
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
 


