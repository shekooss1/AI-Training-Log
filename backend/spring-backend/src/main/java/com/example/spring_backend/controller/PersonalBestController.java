package com.example.spring_backend.controller;
 
import com.example.spring_backend.DTOs.PersonalBestDTO;
import com.example.spring_backend.service.PersonalBestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/api/personalbests")
public class PersonalBestController {
 
    private final PersonalBestService personalBestService;
 
    public PersonalBestController(PersonalBestService personalBestService) {
        this.personalBestService = personalBestService;
    }
 
    @PostMapping
    public ResponseEntity<PersonalBestDTO> create(Long swimmerId,@RequestBody PersonalBestDTO dto) {
        try {
            return personalBestService.addPersonalBest(swimmerId,dto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @GetMapping
    public ResponseEntity<List<PersonalBestDTO>> getAll() {
        try {
            return ResponseEntity.ok(personalBestService.getAllPersonalBests());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<PersonalBestDTO> getById(@PathVariable Long id) {
        try {
            return personalBestService.getPersonalBestById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<PersonalBestDTO> update(@PathVariable Long id, @RequestBody PersonalBestDTO dto) {
        try {
            return personalBestService.updatePersonalBest(id, dto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            return personalBestService.deletePersonalBest(id)
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
 




