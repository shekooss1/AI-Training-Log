package com.example.spring_backend.controller;
 
import com.example.spring_backend.DTOs.SetDTO;
import com.example.spring_backend.service.SetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/api/dailylogs/{logId}/sets")
public class SetController {
 
    private final SetService setService;
 
    public SetController(SetService setService) {
        this.setService = setService;
    }
 
    @PostMapping
    public ResponseEntity<SetDTO> create(@PathVariable Long logId, @RequestBody SetDTO dto) {
        try {
          return setService.addSetToLog(logId, dto)
          .map(ResponseEntity::ok)
          .orElse(ResponseEntity.notFound().build());
         
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @GetMapping
    public ResponseEntity<List<SetDTO>> getAll(@PathVariable Long logId) {
        try {
            return ResponseEntity.ok(setService.getSetsForLog(logId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<SetDTO> getById(@PathVariable Long logId, @PathVariable Long id) {
        try {
            return setService.getSetById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<SetDTO> update(@PathVariable Long logId, @PathVariable Long id, @RequestBody SetDTO dto) {
        try {
            return setService.updateSet(id, dto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long logId, @PathVariable Long id) {
        try {
            return setService.deleteSet(id)
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
 
 