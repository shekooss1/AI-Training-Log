package com.example.spring_backend.controller;
 
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<GymDTO> create(@PathVariable Long logId, @RequestBody @Valid GymDTO dto, Authentication authentication) {
        return gymEntryService.addGymToLog(logId, dto,authentication.getName())
             .map(ResponseEntity::ok)
             .orElse(ResponseEntity.notFound().build());
       }
 
    @GetMapping
    public ResponseEntity<List<GymDTO>> getAll(@PathVariable Long logId,Authentication authentication) {

            return ResponseEntity.ok(gymEntryService.getGymForLog(logId, authentication.getName()));
       }
 
    @GetMapping("/{id}")
    public ResponseEntity<GymDTO> getById(@PathVariable Long id,Authentication authentication) {
            return gymEntryService.getGymById(id, authentication.getName())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
       }
 
    @PutMapping("/{id}")
    public ResponseEntity<GymDTO> update(@PathVariable Long id, @RequestBody @Valid GymDTO dto,Authentication authentication) {
            return gymEntryService.updateGym(id, dto, authentication.getName())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
       }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,Authentication authentication) {
            return gymEntryService.deleteGym(id, authentication.getName())
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.notFound().build();
       }
}
