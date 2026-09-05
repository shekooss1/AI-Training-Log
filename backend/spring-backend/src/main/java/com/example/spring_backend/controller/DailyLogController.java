package com.example.spring_backend.controller;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_backend.DTOs.DailyLogDTO;
import com.example.spring_backend.service.DailyLogService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;




@RestController
@RequestMapping("/api/dailylogs")
public class DailyLogController {
DailyLogService dls ;

public DailyLogController(DailyLogService dls) {
    this.dls = dls;
}

@PostMapping
public ResponseEntity<DailyLogDTO> createLog(@RequestBody @Valid DailyLogDTO dto, Authentication authentication) {
 String email=authentication.getName();
    DailyLogDTO created = dls.createDailyLog(dto,email);
       return  ResponseEntity.status(HttpStatus.CREATED).body(created);

}

@GetMapping
public ResponseEntity<List<DailyLogDTO>> getAll(Authentication authentication) {
String email=authentication.getName();
   List<DailyLogDTO> list =  dls.getAllLogs(email);
   return ResponseEntity.status(HttpStatus.OK).body(list);


}

@GetMapping("/{id}")
public ResponseEntity<DailyLogDTO> getById(@PathVariable Long id,Authentication authentication) {
 String email = authentication.getName();
        return dls.getDailyLogById(id,email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());


    }

@PutMapping("/{id}")
public ResponseEntity<DailyLogDTO> updateLog(@PathVariable Long id, @RequestBody @Valid DailyLogDTO dto,Authentication authentication) {
        String email = authentication.getName();
        return dls.updateDailyLog(id, dto,email).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        
}
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteLog(@PathVariable Long id, Authentication authentication) {
        String email = authentication.getName();
        boolean deleted = dls.deleteDailyLog(id,email);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
}
}