package com.example.spring_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.spring_backend.DTOs.SleepDTO;
import com.example.spring_backend.model.Sleep;
import com.example.spring_backend.repository.DailyLogRepository;
import com.example.spring_backend.repository.SleepRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SleepEntryService {

    private final SleepRepository sleepRepository;
    private final DailyLogRepository dailyLogRepository;

    public SleepEntryService(SleepRepository sleepRepository, DailyLogRepository dailyLogRepository) {
        this.sleepRepository = sleepRepository;
        this.dailyLogRepository = dailyLogRepository;
    }

    public Optional<SleepDTO> addSleepToLog(Long dailyLogId, SleepDTO dto) {
        return dailyLogRepository.findById(dailyLogId).map(dailyLog -> {
            Sleep sleep = dto.toEntity();
            sleep.setDailyLog(dailyLog);
            Sleep saved = sleepRepository.save(sleep);
            return SleepDTO.from(saved);
        });
    }

    public List<SleepDTO> getSleepForLog(Long dailyLogId) {
        return sleepRepository.findByDailyLogId(dailyLogId)
            .stream()
            .map(SleepDTO::from)
            .toList();
    }

    public Optional<SleepDTO> updateSleep(Long sleepId, SleepDTO dto) {
        return sleepRepository.findById(sleepId).map(sleep -> {
            sleep.setHours(dto.hours());
            sleep.setNotes(dto.notes());
            return SleepDTO.from(sleepRepository.save(sleep));
        });
    }

    public boolean deleteSleep(Long sleepId) {
        if (!sleepRepository.existsById(sleepId)) {
            return false;
        }
        sleepRepository.deleteById(sleepId);
        return true;
    }
 public Optional<SleepDTO> getSleepById(Long id){
    return sleepRepository.findById(id).map(SleepDTO::from);
 }
}