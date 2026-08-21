package com.example.spring_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.spring_backend.DTOs.SupplmentDTO;
import com.example.spring_backend.model.Supplment;
import com.example.spring_backend.repository.DailyLogRepository;
import com.example.spring_backend.repository.SupplmentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SupplementEntryService {

    private final SupplmentRepository supplmentRepository;
    private final DailyLogRepository dailyLogRepository;

    public SupplementEntryService(SupplmentRepository supplmentRepository, DailyLogRepository dailyLogRepository) {
        this.supplmentRepository = supplmentRepository;
        this.dailyLogRepository = dailyLogRepository;
    }

    public Optional<SupplmentDTO> addSupplementToLog(Long dailyLogId, SupplmentDTO dto) {
        return dailyLogRepository.findById(dailyLogId).map(dailyLog -> {
            Supplment supplment = dto.toEntity();
            supplment.setDailyLog(dailyLog);
            Supplment saved = supplmentRepository.save(supplment);
            return SupplmentDTO.from(saved);
        });
    }

    public List<SupplmentDTO> getSupplementsForLog(Long dailyLogId) {
        return supplmentRepository.findByDailyLogId(dailyLogId)
            .stream()
            .map(SupplmentDTO::from)
            .toList();
    }

    public Optional<SupplmentDTO> updateSupplement(Long supplementId, SupplmentDTO dto) {
        return supplmentRepository.findById(supplementId).map(supplment -> {
            supplment.setName(dto.name());
            supplment.setDose(dto.dose());
            supplment.setTime(dto.time());
            supplment.setNotes(dto.notes());
            return SupplmentDTO.from(supplmentRepository.save(supplment));
        });
    }

    public boolean deleteSupplement(Long supplementId) {
        if (!supplmentRepository.existsById(supplementId)) {
            return false;
        }
        supplmentRepository.deleteById(supplementId);
        return true;
    }
  public Optional<SupplmentDTO> getSupplmentById(Long id){
    return supplmentRepository.findById(id).map(SupplmentDTO::from);
 }
}