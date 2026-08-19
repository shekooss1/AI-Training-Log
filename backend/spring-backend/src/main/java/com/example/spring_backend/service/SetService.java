package com.example.spring_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.spring_backend.DTOs.SetDTO;
import com.example.spring_backend.model.DailyLog;
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

    public Optional<SetDTO> addSetToLog(Long dailyLogId, SetDTO dto) {
        return dailyLogRepository.findById(dailyLogId).map(dailyLog -> {
            Set set = dto.toEntity();
            set.setDailyLog(dailyLog);
            Set saved = setRepository.save(set);
            return SetDTO.from(saved);
        });
    }

    public List<SetDTO> getSetsForLog(Long dailyLogId) {
        return setRepository.findByDailyLogId(dailyLogId)
            .stream()
            .map(SetDTO::from)
            .toList();
    }

    public Optional<SetDTO> updateSet(Long setId, SetDTO dto) {
        return setRepository.findById(setId).map(set -> {
            set.setReps(dto.reps());
            set.setDistance(dto.distance());
            set.setRest(dto.rest());
            set.setTarget(dto.target());
            set.setIntenisty(dto.intenisty());
            return SetDTO.from(setRepository.save(set));
        });
    }

    public boolean deleteSet(Long setId) {
        if (!setRepository.existsById(setId)) {
            return false;
        }
        setRepository.deleteById(setId);
        return true;
    }
}