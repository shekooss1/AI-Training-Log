package com.example.spring_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SwimmerLogEntry {
private int repNumber   ;  // which rep (1, 2, 3...)
private double actualTime ;   // real time swum for that rep
private double rpe    ;       // optional — how it felt
private String notes ;         // optional — free text 


@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id ;

public SwimmerLogEntry(){}



public SwimmerLogEntry(int repNumber, double actualTime, double rpe, String notes) {
    this.repNumber = repNumber;
    this.actualTime = actualTime;
    this.rpe = rpe;
    this.notes = notes;
}



public int getRepNumber() {
    return repNumber;
}


public void setRepNumber(int repNumber) {
    this.repNumber = repNumber;
}


public double getActualTime() {
    return actualTime;
}


public void setActualTime(double actualTime) {
    this.actualTime = actualTime;
}


public double getRpe() {
    return rpe;
}


public void setRpe(double rpe) {
    this.rpe = rpe;
}


public String getNotes() {
    return notes;
}


public void setNotes(String notes) {
    this.notes = notes;
}


public Long getId() {
    return id;
}


public void setId(Long id) {
    this.id = id;
}



}
