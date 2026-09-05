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
    public ResponseEntity<SleepDTO> create(@PathVariable Long logId, @RequestBody @Valid SleepDTO dto, Authentication authentication) {

            return
             sleepEntryService.addSleepToLog(logId, dto,authentication.getName()).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        }
 
    @GetMapping
    public ResponseEntity<List<SleepDTO>> getAll(@PathVariable Long logId,Authentication authentication) {
            return ResponseEntity.ok(sleepEntryService.getSleepForLog(logId,authentication.getName()));
        }
 
    @GetMapping("/{id}")
    public ResponseEntity<SleepDTO> getById(@PathVariable Long id,Authentication authentication) {
            return sleepEntryService.getSleepById(id,authentication.getName())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
       }
 
    @PutMapping("/{id}")
    public ResponseEntity<SleepDTO> update(@PathVariable Long logId, @PathVariable Long id, @RequestBody @Valid SleepDTO dto, Authentication authentication) {

            return sleepEntryService.updateSleep(id, dto, authentication.getName())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,Authentication authentication) {
            return sleepEntryService.deleteSleep(id, authentication.getName())
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.notFound().build();
       }
}
 
 
