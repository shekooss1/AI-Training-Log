package com.example.spring_backend.DTOs;

import com.example.spring_backend.model.Supplment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SupplmentDTO(Long id,
                           @NotBlank
                           String name,

                           @NotBlank
                           @NotNull
                           double dose,



                           String notes) {
public static SupplmentDTO from(Supplment s){
    return new SupplmentDTO(s.getId(),s.getName(),s.getDose(),s.getNotes());
}
public  Supplment toEntity(){
    return new Supplment(name,dose,notes);
}


}
