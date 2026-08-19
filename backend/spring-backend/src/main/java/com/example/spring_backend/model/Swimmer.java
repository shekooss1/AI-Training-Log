package com.example.spring_backend.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "swimmers")
public class Swimmer {
 private String name , password  ;
 private Especiality especiality  ;
 private Sex sex ;

 
 @Id 
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id ;
 
 private double age ;

 private Stroke stroke ;
 
 @OneToMany(mappedBy="swimmer",cascade=CascadeType.ALL,orphanRemoval=true)
 private List<PersonalBest> PB = new ArrayList<>() ; 

 @OneToMany(mappedBy="swimmer",cascade=CascadeType.ALL,orphanRemoval=true)
 private List<DailyLog> dailyLog = new ArrayList<>();

public Swimmer(){}

    public Swimmer( double age, Especiality especiality, String name, String password, Sex sex, Stroke stroke) {
        this.age = age;
        this.especiality = especiality;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.stroke = stroke;
    }


public Swimmer(double age2, Especiality especiality2, String name2, Sex sex2, Stroke stroke2) {
age=age2;
especiality=especiality2;
name=name2;
sex=sex2;
stroke=stroke2;    }

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

public Stroke getStroke() {
    return stroke;
}

public void setStroke(Stroke stroke) {
    this.stroke = stroke;
}



public List<PersonalBest> getPB() {
    return PB;
}

public void setPB(List<PersonalBest> pB) {
    PB = pB;
}

public List<DailyLog> getDailyLog() {
    return dailyLog;
}

public void setDailyLog(List<DailyLog> dailyLog) {
    this.dailyLog = dailyLog;
}

 
}