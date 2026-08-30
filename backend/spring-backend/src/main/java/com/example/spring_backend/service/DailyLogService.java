package com.example.spring_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.spring_backend.model.Swimmer;
import com.example.spring_backend.repository.SwimmerRepository;
import org.springframework.stereotype.Service;

import com.example.spring_backend.DTOs.DailyLogDTO;
import com.example.spring_backend.model.DailyLog;
import com.example.spring_backend.repository.DailyLogRepository;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@Transactional
public class DailyLogService {
  DailyLogRepository dailyLogRepository ;
    SwimmerRepository swimmerRepository;
  public DailyLogService(DailyLogRepository dailyLogRepository , SwimmerRepository swimmerRepository) {
    this.dailyLogRepository = dailyLogRepository;
    this.swimmerRepository = swimmerRepository;
  }

 public List<DailyLogDTO> getAllLogs(String email) {
    return dailyLogRepository.findAllBySwimmerEmail(email)
        .stream()
        .map(DailyLogDTO::from)
        .collect(Collectors.toList());
}

public Optional<DailyLogDTO> getDailyLogById(Long id,String email) {
  return dailyLogRepository.findByIdAndSwimmer_Email(id,email)
  .map(DailyLogDTO::from) ;
}

    public DailyLogDTO createDailyLog(DailyLogDTO dto, String email) {

        Swimmer swimmer = swimmerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Swimmer not found"));
        DailyLog d = dto.toEntity();
        d.setSwimmer(swimmer);
        return DailyLogDTO.from(dailyLogRepository.save(d));
    }

public Optional<DailyLogDTO> updateDailyLog(Long id,DailyLogDTO dto,String email) {
  return dailyLogRepository.findByIdAndSwimmer_Email(id,email).map(log -> {
   log.setDate(dto.date());
   log.setNotes(dto.notes());
   log.setSessionType(dto.sessionType());

   return DailyLogDTO.from(dailyLogRepository.save(log));
   }); 

  }
  
   public boolean deleteDailyLog(Long id,String email) {
    if(dailyLogRepository.findByIdAndSwimmer_Email(id,email).isEmpty()){
      return false ;
    }
     dailyLogRepository.deleteById(id);
    return true;
   
  }

  }

