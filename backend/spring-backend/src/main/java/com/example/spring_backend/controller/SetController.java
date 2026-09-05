package com.example.spring_backend.controller;
 
import com.example.spring_backend.DTOs.SetDTO;
import com.example.spring_backend.service.SetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<SetDTO> create(@PathVariable Long logId, @RequestBody @Valid SetDTO dto, Authentication authentication) {

            String email = authentication.getName();
          return setService.addSetToLog(logId, dto,email)
          .map(ResponseEntity::ok)
          .orElse(ResponseEntity.notFound().build());
         
        }
 
    @GetMapping
    public ResponseEntity<List<SetDTO>> getAll(@PathVariable Long logId, Authentication authentication) {
            return ResponseEntity.ok(setService.getSetsForLog(logId, authentication.getName()));

    }
 
    @GetMapping("/{id}")
    public ResponseEntity<SetDTO> getById( @PathVariable Long id, Authentication authentication) {
            return setService.getSetById(id,authentication.getName())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
         }
 
    @PutMapping("/{id}")
    public ResponseEntity<SetDTO> update(@PathVariable Long id, @RequestBody @Valid SetDTO dto,Authentication authentication) {

            return setService.updateSet(id,dto,authentication.getName())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
         }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete( @PathVariable Long id,Authentication authentication) {
            return setService.deleteSet(id,authentication.getName())
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.notFound().build();

    }
}
 
 