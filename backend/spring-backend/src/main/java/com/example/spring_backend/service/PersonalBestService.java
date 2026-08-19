package com.example.spring_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.spring_backend.DTOs.PersonalBestDTO;
import com.example.spring_backend.model.PersonalBest;
import com.example.spring_backend.repository.PersonalBestRepository;
import com.example.spring_backend.repository.SwimmerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PersonalBestService {

    private final PersonalBestRepository personalBestRepository;
    private final SwimmerRepository swimmerRepository;

    public PersonalBestService(PersonalBestRepository personalBestRepository, SwimmerRepository swimmerRepository) {
        this.personalBestRepository = personalBestRepository;
        this.swimmerRepository = swimmerRepository;
    }

    public Optional<PersonalBestDTO> addPersonalBest(Long swimmerId, PersonalBestDTO dto) {
        return swimmerRepository.findById(swimmerId).map(swimmer -> {
            PersonalBest pb = dto.toEntity();
            pb.setSwimmer(swimmer);
            PersonalBest saved = personalBestRepository.save(pb);
            return PersonalBestDTO.from(saved);
        });
    }

    public List<PersonalBestDTO> getPersonalBestsForSwimmer(Long swimmerId) {
        return personalBestRepository.findBySwimmerId(swimmerId)
            .stream()
            .map(PersonalBestDTO::from)
            .toList();
    }

    public Optional<PersonalBestDTO> getPersonalBestById(Long id) {
        return personalBestRepository.findById(id)
            .map(PersonalBestDTO::from);
    }

    public Optional<PersonalBestDTO> updatePersonalBest(Long id, PersonalBestDTO dto) {
        return personalBestRepository.findById(id).map(pb -> {
            pb.setDistance(dto.distance());
            pb.setRecord(dto.record());
            pb.setStroke(dto.stroke());
            return PersonalBestDTO.from(personalBestRepository.save(pb));
        });
    }

    public boolean deletePersonalBest(Long id) {
        if (!personalBestRepository.existsById(id)) {
            return false;
        }
        personalBestRepository.deleteById(id);
        return true;
    }
}