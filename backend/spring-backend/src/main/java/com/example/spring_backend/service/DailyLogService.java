package com.example.spring_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.spring_backend.DTOs.DailyLogDTO;
import com.example.spring_backend.model.DailyLog;
import com.example.spring_backend.repository.DailyLogRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DailyLogService {
  DailyLogRepository dailyLogRepository ;

  public DailyLogService(DailyLogRepository dailyLogRepository) {
    this.dailyLogRepository = dailyLogRepository;
  }

 public List<DailyLogDTO> getAllLogs() {
    return dailyLogRepository.findAll()
        .stream()
        .map(DailyLogDTO::from)
        .collect(Collectors.toList());
}

public Optional<DailyLogDTO> getDailyLogById(Long id){
  return dailyLogRepository.findById(id) 
  .map(DailyLogDTO::from) ;
}

public DailyLogDTO createDailyLog(DailyLogDTO dto){
 DailyLog d = dto.toEntity() ;
 return DailyLogDTO.from((DailyLog)dailyLogRepository.save(d));
}

public Optional<DailyLogDTO> updateDailyLog(Long id,DailyLogDTO dto){
  return dailyLogRepository.findById(id).map(log -> {
   log.setDate(dto.date());
   log.setNotes(dto.notes());
   log.setSessionType(dto.sessionType());

   return DailyLogDTO.from(dailyLogRepository.save(log));
   }); 

  }
  
   public boolean deleteDailyLog(Long id){
    if(dailyLogRepository.findById(id).isEmpty()){
      return false ;
    }
     dailyLogRepository.deleteById(id);
    return true;
   
  }

  }

