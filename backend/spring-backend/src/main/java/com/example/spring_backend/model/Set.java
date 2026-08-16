package com.example.spring_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Set {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;

    private int reps , distance ;
    
    private int rest ;

    private String target ;
    
    private double intensity ;

    public Set(){}
    
    public Set( int reps, int distance, int rest, String target, double intenisty) {
        
        this.reps = reps;
        this.distance = distance;
        this.rest = rest;
        this.target = target;
        this.intensity = intenisty;
    }

    public double getOverallDistance() {
        return reps*distance;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public double getIntenisty() {
        return intensity;
    }

    public void setIntenisty(double intenisty) {
        this.intensity = intenisty;
    }


    



}
