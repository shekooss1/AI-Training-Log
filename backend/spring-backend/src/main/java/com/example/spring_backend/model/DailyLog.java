package com.example.spring_backend.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class DailyLog {
   LocalDate date ;
    private SessionType sessionType ;
    private String notes ;         // optional — free text 

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id ;

@OneToMany(mappedBy="dailyLog", cascade = CascadeType.ALL, orphanRemoval = true)
List<Set> sets ;

@OneToMany(mappedBy="dailyLog", cascade = CascadeType.ALL, orphanRemoval = true)
List<Sleep> sleep ;

@OneToMany(mappedBy="dailyLog", cascade = CascadeType.ALL, orphanRemoval = true)
List<Gym> gym ;

@OneToMany(mappedBy="dailyLog", cascade = CascadeType.ALL, orphanRemoval = true)
List<Supplment> supplment ;

@ManyToOne
private Swimmer swimmer ;

public DailyLog() {
}

public DailyLog(LocalDate date, SessionType sessionType, String notes) {
    this.date = date;
    this.sessionType = sessionType;
    this.notes = notes;
}

public List<Set> getSets() {
    return sets;
}

public void setSets(List<Set> sets) {
    this.sets = sets;
}

public List<Sleep> getSleep() {
    return sleep;
}

public void setSleep(List<Sleep> sleep) {
    this.sleep = sleep;
}

public List<Gym> getGym() {
    return gym;
}

public void setGym(List<Gym> gym) {
    this.gym = gym;
}

public List<Supplment> getSupplment() {
    return supplment;
}

public void setSupplment(List<Supplment> supplment) {
    this.supplment = supplment;
}

public LocalDate getDate() {
    return date;
}

public void setDate(LocalDate date) {
    this.date = date;
}

public SessionType getSessionType() {
    return sessionType;
}

public void setSessionType(SessionType sessionType) {
    this.sessionType = sessionType;
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
