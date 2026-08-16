package com.example.spring_backend.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CoachLogEntry {
private String title ;
LocalDate SessionDate;
private Workout worlkout ;

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id ;

public CoachLogEntry(){}



public CoachLogEntry(String title, LocalDate sessionDate, Workout worlkout) {
    this.title = title;
    SessionDate = sessionDate;
    this.worlkout = worlkout;
}



public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}

public LocalDate getSessionDate() {
    return SessionDate;
}

public void setSessionDate(LocalDate sessionDate) {
    SessionDate = sessionDate;
}

public Workout getWorlkout() {
    return worlkout;
}

public void setWorlkout(Workout worlkout) {
    this.worlkout = worlkout;
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}



}
