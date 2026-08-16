package com.example.spring_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
 private String name , password  ;
 private Especiality especiality  ;
 private Sex sex ;
 private Role role ;
 
 @Id 
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id ;
 
 private double age ;

 private Stroke stroke ;
 
public User(){}


 

 public User(String name, String password, Especiality especiality, Sex sex, Role role, double age) {
   this.name = name;
   this.password = password;
   this.especiality = especiality;
   this.sex = sex;
   this.role = role;
   this.age = age;
}




 public String getName() {
    return name;
 }

 public void setName(String name) {
    this.name = name;
 }

 public String getPassword() {
    return password;
 }

 public void setPassword(String password) {
    this.password = password;
 }

 public Especiality getEspeciality() {
    return especiality;
 }

 public void setEspeciality(Especiality especiality) {
    this.especiality = especiality;
 }

 public Sex getSex() {
    return sex;
 }

 public void setSex(Sex sex) {
    this.sex = sex;
 }

 public Long getId() {
    return id;
 }

 public void setId(Long id) {
    this.id = id;
 }

 public double getAge() {
    return age;
 }

 public void setAge(double age) {
    this.age = age;
 }




 public Role getRole() {
   return role;
 }




 public void setRole(Role role) {
   this.role = role;
 }




 public Stroke getStroke() {
   return stroke;
 }




 public void setStroke(Stroke stroke) {
   this.stroke = stroke;
 } 

 

}
