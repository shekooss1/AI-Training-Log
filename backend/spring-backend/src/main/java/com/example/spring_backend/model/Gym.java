package com.example.spring_backend.model;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Gym {
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id ;

 private String exercise, notes ;
private double sets, reps, weight;

@ManyToOne
private DailyLog dailyLog ;

public Gym() {
}


public Gym(Long id, String exercise, String notes, double sets, double reps, double weight) {
    this.id = id;
    this.exercise = exercise;
    this.notes = notes;
    this.sets = sets;
    this.reps = reps;
    this.weight = weight;
}


public Long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}
public String getExercise() {
    return exercise;
}
public void setExercise(String exercise) {
    this.exercise = exercise;
}
public String getNotes() {
    return notes;
}
public void setNotes(String notes) {
    this.notes = notes;
}
public double getSets() {
    return sets;
}
public void setSets(double sets) {
    this.sets = sets;
}
public double getReps() {
    return reps;
}
public void setReps(double reps) {
    this.reps = reps;
}
public double getWeight() {
    return weight;
}
public void setWeight(double weight) {
    this.weight = weight;
}


public DailyLog getDailyLog() {
    return dailyLog;
}


public void setDailyLog(DailyLog dailyLog) {
    this.dailyLog = dailyLog;
}


}
