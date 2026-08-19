package com.example.spring_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.spring_backend.DTOs.GymDTO;
import com.example.spring_backend.model.Gym;
import com.example.spring_backend.repository.GymRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GymService {
  GymRepository gymRepository ;

  
 public GymService(GymRepository gymRepository) {
    this.gymRepository = gymRepository;
  }

 public List<GymDTO> getAllGym() {
    return gymRepository.findAll()
        .stream()
        .map(GymDTO::from)
        .collect(Collectors.toList());
}

public Optional<GymDTO> getGymById(Long id){
  return gymRepository.findById(id) 
  .map(GymDTO::from) ;
}

public GymDTO createDailyLog(GymDTO dto){
 Gym d = dto.toEntity() ;
 return GymDTO.from(gymRepository.save(d));
}

public Optional<GymDTO> updateDailyLog(Long id,GymDTO dto){
  return gymRepository.findById(id).map(log -> {
   log.setExercise(dto.exercise());
   log.setNotes(dto.notes());
   log.setWeight(dto.weight());

   return GymDTO.from(gymRepository.save(log));
   }); 

  }
  
   public boolean deleteGym(Long id){
    if(gymRepository.findById(id).isEmpty()){
      return false ;
    }
     gymRepository.deleteById(id);
    return true;
   
  }

  }

