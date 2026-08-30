    package com.example.spring_backend.service;

    import java.util.List;
    import java.util.Optional;
    import java.util.stream.Collectors;

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

        public Optional<PersonalBestDTO> addPersonalBest(Long swimmerId, PersonalBestDTO dto,String email) {
            return swimmerRepository.findByEmail(email).map(swimmer -> {
                PersonalBest pb = dto.toEntity();
                pb.setSwimmer(swimmer);
                PersonalBest saved = personalBestRepository.save(pb);
                return PersonalBestDTO.from(saved);
            });
        }

        public List<PersonalBestDTO> getPersonalBestsForSwimmer(Long swimmerId,String email) {
            return personalBestRepository.findAllBySwimmer_Email(email)
                .stream()
                .map(PersonalBestDTO::from)
                .toList();
        }

        public Optional<PersonalBestDTO> getPersonalBestById(Long id,String email) {
            return personalBestRepository.findByIdAndSwimmer_Email(id,email)
                .map(PersonalBestDTO::from);
        }

        public Optional<PersonalBestDTO> updatePersonalBest(Long id, PersonalBestDTO dto,String email) {
            return personalBestRepository.findByIdAndSwimmer_Email(id,email).map(pb -> {
                pb.setDistance(dto.distance());
                pb.setRecord(dto.record());
                pb.setStroke(dto.stroke());
                return PersonalBestDTO.from(personalBestRepository.save(pb));
            });
        }

        public boolean deletePersonalBest(Long id,String email) {
            if (personalBestRepository.findByIdAndSwimmer_Email(id,email).isEmpty()) {
                return false;
            }
            personalBestRepository.deleteById(id);
            return true;
        }

        public List<PersonalBestDTO> getAllPersonalBests(){
             return personalBestRepository.findAll().stream().map(PersonalBestDTO::from).collect(Collectors.toList());
        }

    }