package com.example.spring_backend.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CoachLogEntry {
private String title ;
LocalDate sessionDate;

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id ;

public CoachLogEntry(){}



public CoachLogEntry(String title, LocalDate sessionDate) {
    this.title = title;
    this.sessionDate = sessionDate;
}



public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}

public LocalDate getSessionDate() {
    return sessionDate;
}

public void setSessionDate(LocalDate sessionDate) {
    this.sessionDate = sessionDate;
}


public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}



}
