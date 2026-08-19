package com.example.spring_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Sleep {
 private double hours , rating ;
 private String notes;

 @Id 
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id ;

 @JsonIgnore
@ManyToOne(fetch=FetchType.LAZY)
private DailyLog dailyLog ;

 public Sleep(){}
 
 
 public Sleep(double hours, double rating, String notes) {
    this.hours = hours;
    this.rating = rating;
    this.notes = notes;
}


 public Sleep(double hours2, String notes2) {
 hours=hours2;
 notes=notes2;
}


 public double getHours() {
    return hours;
 }

 public void setHours(double hours) {
    this.hours = hours;
 }

 public double getRating() {
    return rating;
 }

 public void setRating(double rating) {
    this.rating = rating;
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


 public DailyLog getDailyLog() {
    return dailyLog;
 }


 public void setDailyLog(DailyLog dailyLog) {
    this.dailyLog = dailyLog;
 }

 

}
