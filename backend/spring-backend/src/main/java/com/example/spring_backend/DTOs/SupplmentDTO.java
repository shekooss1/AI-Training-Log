package com.example.spring_backend.DTOs;

import com.example.spring_backend.model.Supplment;

public record SupplmentDTO(String name, double dose, double time,String notes) {
public static SupplmentDTO from(Supplment s){
    return new SupplmentDTO(s.getName(),s.getDose(),s.getTime(),s.getNotes());
}
public  Supplment toEntity(){
    return new Supplment(name,dose,time,notes);
}


}
