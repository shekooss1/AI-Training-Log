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
    public ResponseEntity<SupplmentDTO> create(@PathVariable Long logId, @RequestBody SupplmentDTO dto) {
        try {
            return supplementEntryService.addSupplementToLog(logId, dto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
        
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @GetMapping
    public ResponseEntity<List<SupplmentDTO>> getAll(@PathVariable Long logId) {
        try {
            return ResponseEntity.ok(supplementEntryService.getSupplementsForLog(logId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<SupplmentDTO> getById(@PathVariable Long logId, @PathVariable Long id) {
        try {
            return supplementEntryService.getSupplmentById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<SupplmentDTO> update(@PathVariable Long logId, @PathVariable Long id, @RequestBody SupplmentDTO dto) {
        try {
            return supplementEntryService.updateSupplement(id, dto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long logId, @PathVariable Long id) {
        try {
            return supplementEntryService.deleteSupplement(id)
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
 
