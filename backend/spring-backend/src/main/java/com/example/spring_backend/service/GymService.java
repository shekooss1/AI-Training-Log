package com.example.spring_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.spring_backend.DTOs.GymDTO;
import com.example.spring_backend.model.Gym;
import com.example.spring_backend.repository.DailyLogRepository;
import com.example.spring_backend.repository.GymRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GymService {

    private final GymRepository gymRepository;
    private final DailyLogRepository dailyLogRepository;

    public GymService(GymRepository gymRepository, DailyLogRepository dailyLogRepository) {
        this.gymRepository = gymRepository;
        this.dailyLogRepository = dailyLogRepository;
    }

    public Optional<GymDTO> addGymToLog(Long dailyLogId, GymDTO dto,String email) {
        return dailyLogRepository.findByIdAndSwimmer_Email(dailyLogId,email).map(dailyLog -> {
            Gym gym = dto.toEntity();
            gym.setDailyLog(dailyLog);
            Gym saved = gymRepository.save(gym);
            return GymDTO.from(saved);
        });
    }

    public List<GymDTO> getGymForLog(Long dailyLogId,String email) {
        return gymRepository.findAllByDailyLog_IdAndDailyLog_Swimmer_Email(dailyLogId,email)
            .stream()
            .map(GymDTO::from)
            .toList();
    }

    public Optional<GymDTO> updateGym(Long gymId, GymDTO dto, String email) {
        return gymRepository.findByIdAndDailyLog_Swimmer_Email(gymId,email).map(gym -> {
            gym.setExercise(dto.exercise());;
            gym.setNotes(dto.notes());
            gym.setReps(dto.reps());
            gym.setSets(dto.sets());
            gym.setWeight(dto.weight());
            return GymDTO.from(gymRepository.save(gym));
        });
    }

    public boolean deleteGym(Long sleepId,String email) {
        if (gymRepository.findByIdAndDailyLog_Swimmer_Email(sleepId,email).isEmpty()) {
            return false;
        }
        gymRepository.deleteById(sleepId);
        return true;
    }
  public Optional<GymDTO> getGymById( Long id,String email) {
    return gymRepository.findByIdAndDailyLog_Swimmer_Email(id,email).map(GymDTO::from);
}

}