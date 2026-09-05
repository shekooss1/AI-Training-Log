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

import com.example.spring_backend.DTOs.SupplmentDTO;
import com.example.spring_backend.service.SupplementEntryService;
 
@RestController
@RequestMapping("/api/dailylogs/{logId}/supplements")
public class SupplementEntryController {
 
    private final SupplementEntryService supplementEntryService;
 
    public SupplementEntryController(SupplementEntryService supplementEntryService) {
        this.supplementEntryService = supplementEntryService;
    }
 
    @PostMapping
    public ResponseEntity<SupplmentDTO> create(@PathVariable Long logId, @RequestBody @Valid SupplmentDTO dto, Authentication authentication) {
            return supplementEntryService.addSupplementToLog(logId, dto,authentication.getName())
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
        

    }
 
    @GetMapping
    public ResponseEntity<List<SupplmentDTO>> getAll(@PathVariable Long logId, Authentication authentication) {
            return ResponseEntity.ok(supplementEntryService.getSupplementsForLog(logId, authentication.getName()));

    }
 
    @GetMapping("/{id}")
    public ResponseEntity<SupplmentDTO> getById(@PathVariable Long id, Authentication authentication) {
            return supplementEntryService.getSupplmentById(id,authentication.getName())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
 
    @PutMapping("/{id}")
    public ResponseEntity<SupplmentDTO> update( @PathVariable Long id, @RequestBody @Valid SupplmentDTO dto, Authentication authentication) {
            return supplementEntryService.updateSupplement(id, dto, authentication.getName())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
          }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete( @PathVariable Long id,Authentication authentication) {
            return supplementEntryService.deleteSupplement(id, authentication.getName())
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.notFound().build();
    }
}
 
