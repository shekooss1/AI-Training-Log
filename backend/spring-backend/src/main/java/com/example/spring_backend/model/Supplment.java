package com.example.spring_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Supplment {
 private String name ;
 private double dose ;
 private String notes ;

  @Id 
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id ;

@JsonIgnore
 @ManyToOne(fetch=FetchType.LAZY)
private DailyLog dailyLog ;

 public Supplment(){}
 
 
 public Supplment(String name, double dose, String notes) {
    this.name = name;
    this.dose = dose;
    this.notes= notes;
}


 public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getDose() {
    return dose;
  }

  public void setDose(double dose) {
    this.dose = dose;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getNotes() {
    return notes;
  }


  public void setNotes(String notes) {
    this.notes = notes;
  }


  public DailyLog getDailyLog() {
    return dailyLog;
  }


  public void setDailyLog(DailyLog dailyLog) {
    this.dailyLog = dailyLog;
  }


 
}
