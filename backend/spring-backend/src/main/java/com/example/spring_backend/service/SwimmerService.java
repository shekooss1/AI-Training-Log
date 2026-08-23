package com.example.spring_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.spring_backend.DTOs.SwimmerDTO;
import com.example.spring_backend.model.Swimmer;
import com.example.spring_backend.repository.SwimmerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SwimmerService {
  SwimmerRepository swimmerRepository;

  private static class EmailAlreadyInUseException extends RuntimeException {
    private EmailAlreadyInUseException(String email) {
      super("Email is already in use: " + email);
    }
  }

  public SwimmerService(SwimmerRepository swimmerRepository) {
    this.swimmerRepository = swimmerRepository;
  }

  public List<SwimmerDTO> getAllSwimmers() {
    return swimmerRepository.findAll()
        .stream()
        .map(SwimmerDTO::from)
        .collect(Collectors.toList());
  }

  public Optional<SwimmerDTO> getSwimmerById(Long id) {
    return swimmerRepository.findById(id)
        .map(SwimmerDTO::from);
  }

  public SwimmerDTO createSwimmer(SwimmerDTO dto) {
    if (swimmerRepository.findByEmail(dto.email()).isPresent()) {
      throw new EmailAlreadyInUseException(dto.email());
    }
    Swimmer s = dto.toEntity();
    return SwimmerDTO.from(swimmerRepository.save(s));
  }

  public Optional<SwimmerDTO> updateSwimmer(Long id, SwimmerDTO dto) {
    return swimmerRepository.findById(id).map(swimmer -> {
      swimmerRepository.findByEmail(dto.email()).ifPresent(existing -> {
        if (!existing.getId().equals(id)) {
          throw new EmailAlreadyInUseException(dto.email());
        }
      });

      swimmer.setName(dto.name());
      swimmer.setEmail(dto.email());
      swimmer.setPassword(dto.password());
      swimmer.setSex(dto.sex());
      swimmer.setAge(dto.age());
      swimmer.setStroke(dto.stroke());
      swimmer.setEspeciality(dto.especiality());

      return SwimmerDTO.from(swimmerRepository.save(swimmer));
    });
  }

  public boolean deleteSwimmer(Long id) {
    if (swimmerRepository.findById(id).isEmpty()) {
      return false;
    }
    swimmerRepository.deleteById(id);
    return true;
  }

}