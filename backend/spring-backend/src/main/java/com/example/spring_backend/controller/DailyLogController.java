package com.example.spring_backend.controller;

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
public ResponseEntity<DailyLogDTO> createLog(@RequestBody DailyLogDTO dto) {
   try {
    DailyLogDTO created = dls.createDailyLog(dto);   
       return  ResponseEntity.status(HttpStatus.CREATED).body(created);
   } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
   
}
}

@GetMapping
public ResponseEntity<List<DailyLogDTO>> getAll() {
try {
   List<DailyLogDTO> list =  dls.getAllLogs();
   return ResponseEntity.status(HttpStatus.OK).body(list);

} catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

}
}

@GetMapping("/{id}")
public ResponseEntity<DailyLogDTO> getById(@PathVariable Long id) {
    try {
 return dls.getDailyLogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

} catch (Exception e) {
                 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }
}
@PutMapping("/{id}")
public ResponseEntity<DailyLogDTO> updateLog(@PathVariable Long id, @RequestBody DailyLogDTO dto) {
    try {
        return dls.updateDailyLog(id, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        
    } catch (Exception e) {
                         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }
}
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteLog(@PathVariable Long id) {
    try {
        boolean deleted = dls.deleteDailyLog(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
}