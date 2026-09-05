package com.example.spring_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.spring_backend.DTOs.SwimmerResponseDTO;
import com.example.spring_backend.DTOs.SwimmerUpdateDto;
import com.example.spring_backend.exception.EmailAlreadyInUseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring_backend.DTOs.SwimmerDTO;
import com.example.spring_backend.model.Swimmer;
import com.example.spring_backend.repository.SwimmerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SwimmerService {
  SwimmerRepository swimmerRepository;
   public BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder(12);


   @Autowired
   JwtService jwtService;
   @Autowired
   AuthenticationManager authManager;





  public SwimmerService(SwimmerRepository swimmerRepository) {
    this.swimmerRepository = swimmerRepository;
  }


  public Optional<SwimmerResponseDTO> getSwimmerById(Long id,String email) {
    return swimmerRepository.findByIdAndEmail(id,email)
        .map(SwimmerResponseDTO::from);
  }

  public SwimmerResponseDTO createSwimmer(SwimmerDTO dto) {
    dto = new SwimmerDTO(dto.id(), dto.age(),dto.especiality(),dto.name(),dto.email(),encoder.encode(dto.password()), dto.sex(), dto.stroke());
    if (swimmerRepository.findByEmail(dto.email()).isPresent()) {
      throw new EmailAlreadyInUseException(dto.email());
    }
    Swimmer s = dto.toEntity();
    return SwimmerResponseDTO.from(swimmerRepository.save(s));
  }

  public Optional<SwimmerResponseDTO> updateSwimmer(Long id, SwimmerUpdateDto dto, String email) {
    return swimmerRepository.findByIdAndEmail(id,email).map(swimmer -> {
      swimmerRepository.findByEmail(dto.email()).ifPresent(existing -> {
        if (!existing.getId().equals(id)) {
          throw new EmailAlreadyInUseException(dto.email());
        }
      });

      swimmer.setName(dto.name());
      swimmer.setEmail(dto.email());
      if (dto.password() != null) {
        swimmer.setPassword(encoder.encode(dto.password()));
      }
      swimmer.setSex(dto.sex());
      swimmer.setAge(dto.age());
      swimmer.setStroke(dto.stroke());
      swimmer.setEspeciality(dto.especiality());

      return SwimmerResponseDTO.from(swimmerRepository.save(swimmer));
    });
  }

  public void deleteSwimmer(Long id, String email) {
    if (swimmerRepository.findByIdAndEmail(id, email).isEmpty()) {
      throw new UsernameNotFoundException(swimmerRepository.findById(id).get().getEmail());
    }
    swimmerRepository.deleteById(id);
  }

}