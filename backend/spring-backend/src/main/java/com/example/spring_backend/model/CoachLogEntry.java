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
private Workout workout ;

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id ;

public CoachLogEntry(){}



public CoachLogEntry(String title, LocalDate sessionDate, Workout workout) {
    this.title = title;
    sessionDate = sessionDate;
    this.workout = workout;
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

public Workout getWorlkout() {
    return workout;
}

public void setWorkout(Workout workout) {
    this.workout = workout;
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}



}
