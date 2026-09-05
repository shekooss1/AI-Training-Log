package com.example.spring_backend.service;

import com.example.spring_backend.DTOs.SwimmerDTO;
import com.example.spring_backend.DTOs.SwimmerResponseDTO;
import com.example.spring_backend.model.Swimmer;
import com.example.spring_backend.repository.SwimmerRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.spring_backend.model.Especiality.sprint;
import static com.example.spring_backend.model.Sex.male;
import static com.example.spring_backend.model.Stroke.sf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SwimmerServiceTest {

@Mock
    SwimmerRepository swimmerRepository;

    @InjectMocks
    SwimmerService swimmerService;

    @BeforeAll
    public static void print(){
        System.out.println("Before All");
    }


    @Test
    void  addSwimmer(){
        System.out.println("MyFirstTest");
        SwimmerDTO swimmerDTO = new SwimmerDTO( 10L,20,sprint,"Ali","ali@gmail.com","Shekoo2006@",male,sf);
        when(swimmerRepository.save(any(Swimmer.class))).thenReturn(swimmerDTO.toEntity());
SwimmerResponseDTO  dto = swimmerService.createSwimmer(swimmerDTO);
        Assertions.assertEquals(swimmerDTO.email(),dto.email());
    }

    @Test
    void  addSwimmerThrowException(){
        System.out.println("MyFirstTest");
        SwimmerDTO swimmerDTO = new SwimmerDTO( 10L,20,sprint,"","ali@gmail.com","Shekoo2006@",male,sf);
        Assertions.assertThrows(RuntimeException.class,()-> swimmerService.createSwimmer(swimmerDTO));
    }
@Test
    void deleteSwimmerByIdTest(){
    SwimmerDTO swimmerDTO = new SwimmerDTO( 10L,20,sprint,"Ali","ali@gmail.com","Shekoo2006@",male,sf);

    doNothing().when(swimmerRepository).deleteById(10L);
    when(swimmerRepository.findByIdAndEmail(10L,"ali@gmail.com"))
            .thenReturn(Optional.of(swimmerDTO.toEntity()));
swimmerService.deleteSwimmer(10L,"ali@gmail.com");
        verify(swimmerRepository,times(1)).deleteById(10L);

    }

}


