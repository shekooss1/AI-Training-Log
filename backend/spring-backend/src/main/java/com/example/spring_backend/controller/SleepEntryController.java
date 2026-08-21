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

import com.example.spring_backend.DTOs.SleepDTO;
import com.example.spring_backend.service.SleepEntryService;
 
@RestController
@RequestMapping("/api/dailylogs/{logId}/sleep")
public class SleepEntryController {
 
    private final SleepEntryService sleepEntryService;
 
    public SleepEntryController(SleepEntryService sleepEntryService) {
        this.sleepEntryService = sleepEntryService;
    }
 
    @PostMapping
    public ResponseEntity<SleepDTO> create(@PathVariable Long logId, @RequestBody SleepDTO dto) {
        try {
            return
             sleepEntryService.addSleepToLog(logId, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
             } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @GetMapping
    public ResponseEntity<List<SleepDTO>> getAll(@PathVariable Long logId) {
        try {
            return ResponseEntity.ok(sleepEntryService.getSleepForLog(logId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<SleepDTO> getById(@PathVariable Long logId, @PathVariable Long id) {
        try {
            return sleepEntryService.getSleepById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<SleepDTO> update(@PathVariable Long logId, @PathVariable Long id, @RequestBody SleepDTO dto) {
        try {
            return sleepEntryService.updateSleep(id, dto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long logId, @PathVariable Long id) {
        try {
            return sleepEntryService.deleteSleep(id)
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
 
 
