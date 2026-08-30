package com.example.spring_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.spring_backend.DTOs.SetDTO;
import com.example.spring_backend.model.Set;
import com.example.spring_backend.repository.DailyLogRepository;
import com.example.spring_backend.repository.SetRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SetService {

    private final SetRepository setRepository;
    private final DailyLogRepository dailyLogRepository;

    public SetService(SetRepository setRepository, DailyLogRepository dailyLogRepository) {
        this.setRepository = setRepository;
        this.dailyLogRepository = dailyLogRepository;
    }

    public Optional<SetDTO> addSetToLog(Long dailyLogId, SetDTO dto, String email) {
        return dailyLogRepository.findByIdAndSwimmer_Email(dailyLogId,email).map(dailyLog -> {
            Set set = dto.toEntity();
            set.setDailyLog(dailyLog);
            Set saved = setRepository.save(set);
            return SetDTO.from(saved);
        });
    }

    public List<SetDTO> getSetsForLog(Long dailyLogId,String email) {
        return setRepository.findAllByDailyLog_IdAndDailyLog_Swimmer_Email(dailyLogId,email)
            .stream()
            .map(SetDTO::from)
            .toList();
    }

    public Optional<SetDTO> updateSet(Long setId, SetDTO dto,String email) {
        return setRepository.findByIdAndDailyLog_Swimmer_Email(setId,email).map(set -> {
            set.setReps(dto.reps());
            set.setDistance(dto.distance());
            set.setRest(dto.rest());
            set.setTarget(dto.target());
            set.setIntenisty(dto.intenisty());
            return SetDTO.from(setRepository.save(set));
        });
    }

    public boolean deleteSet(Long setId, String email) {
        if (setRepository.findByIdAndDailyLog_Swimmer_Email(setId, email).isEmpty()) {
            return false;
        }
        setRepository.deleteById(setId);
        return true;
    }

    public Optional<SetDTO> getSetById(Long id,String email) {
       return setRepository.findByIdAndDailyLog_Swimmer_Email(id,email).map(SetDTO::from);
    }
    
}