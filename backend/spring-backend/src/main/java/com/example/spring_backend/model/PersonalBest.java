package com.example.spring_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PersonalBest {
 private  double distance ;
 private double record ;
 private Stroke stroke ;

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id ;

@ManyToOne
private Swimmer swimmer ;

 public PersonalBest(){}


 public PersonalBest(double distance, double record, Stroke stroke) {
    this.distance = distance;
    this.record = record;
    this.stroke = stroke;
 }


 public double getDistance() {
    return distance;
 }


 public void setDistance(double distance) {
    this.distance = distance;
 }


 public double getRecord() {
    return record;
 }


 public void setRecord(double record) {
    this.record = record;
 }


 public Stroke getStroke() {
    return stroke;
 }


 public void setStroke(Stroke stroke) {
    this.stroke = stroke;
 }


 public Long getId() {
    return id;
 }


 public void setId(Long id) {
    this.id = id;
 }

 




}
