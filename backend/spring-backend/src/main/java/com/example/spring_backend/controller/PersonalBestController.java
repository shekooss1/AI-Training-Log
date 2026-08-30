package com.example.spring_backend.controller;
 
import com.example.spring_backend.DTOs.PersonalBestDTO;
import com.example.spring_backend.service.PersonalBestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/api/swimmers/{swimmerId}/personalbests")
public class PersonalBestController {
 
    private final PersonalBestService personalBestService;
 
    public PersonalBestController(PersonalBestService personalBestService) {
        this.personalBestService = personalBestService;
    }
 
    @PostMapping
    public ResponseEntity<PersonalBestDTO> create(@PathVariable Long swimmerId, @RequestBody PersonalBestDTO dto, Authentication authentication) {
        try {
            return personalBestService.addPersonalBest(swimmerId,dto,authentication.getName())
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @GetMapping
    public ResponseEntity<List<PersonalBestDTO>> getAll(@PathVariable Long swimmerId, Authentication authentication) {
        try {
            return ResponseEntity.ok(personalBestService.getPersonalBestsForSwimmer(swimmerId,authentication.getName()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<PersonalBestDTO> getById(@PathVariable Long id,Authentication authentication) {
        try {
            return personalBestService.getPersonalBestById(id,authentication.getName())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<PersonalBestDTO> update(@PathVariable Long id, @RequestBody PersonalBestDTO dto, Authentication authentication) {
        try {
            return personalBestService.updatePersonalBest(id, dto,authentication.getName())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,Authentication authentication) {
        try {
            return personalBestService.deletePersonalBest(id, authentication.getName())
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
 




