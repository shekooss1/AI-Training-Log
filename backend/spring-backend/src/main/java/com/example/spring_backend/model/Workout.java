package com.example.spring_backend.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Workout {
   private LocalDate date ;
   private double distance ;
   private Especiality especiality ;
   
   @Id
   @GeneratedValue(strategy =GenerationType.IDENTITY )
   private Long id ; 
   
   List<Set> sets ;
   
   public Workout(){}

    public Workout(LocalDate date, double distance, Especiality especiality) {
        this.date = date;
        this.distance = distance;
        this.especiality = especiality;
    }

   public LocalDate getDate() {
      return date;
   }

   public void setDate(LocalDate date) {
      this.date = date;
   }

   public double getDistance() {
      return distance;
   }

   public void setDistance(double distance) {
      this.distance = distance;
   }

   public Especiality getEspeciality() {
      return especiality;
   }

   public void setEspeciality(Especiality especiality) {
      this.especiality = especiality;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public List<Set> getSets() {
      return sets;
   }

   public void setSets(List<Set> sets) {
      this.sets = sets;
   }
   
   
}
