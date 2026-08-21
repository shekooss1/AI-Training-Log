package com.example.spring_backend.controller;
 
import java.util.List;

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

import com.example.spring_backend.DTOs.GymDTO;
import com.example.spring_backend.service.GymService;
 
@RestController
@RequestMapping("/api/dailylogs/{logId}/gym")
public class GymEntryController {
 
    private final GymService gymEntryService;
 
    public GymEntryController(GymService gymEntryService) {
        this.gymEntryService = gymEntryService;
    }
 
    @PostMapping
    public ResponseEntity<GymDTO> create(@PathVariable Long logId, @RequestBody GymDTO dto) {
        try {
             return gymEntryService.addGymToLog(logId, dto)
             .map(ResponseEntity::ok)
             .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @GetMapping
    public ResponseEntity<List<GymDTO>> getAll(@PathVariable Long logId) {
        try {
            return ResponseEntity.ok(gymEntryService.getGymForLog(logId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<GymDTO> getById(@PathVariable Long logId, @PathVariable Long id) {
        try {
            return gymEntryService.getGymById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<GymDTO> update(@PathVariable Long logId, @PathVariable Long id, @RequestBody GymDTO dto) {
        try {
            return gymEntryService.updateGym(id, dto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long logId, @PathVariable Long id) {
        try {
            return gymEntryService.deleteGym(id)
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
