package com.example.spring_backend.service;

import org.springframework.stereotype.Service;

import com.example.spring_backend.model.DailyLog;
import com.example.spring_backend.model.Especiality;
import com.example.spring_backend.model.SessionType;
import com.example.spring_backend.model.Set;
import com.example.spring_backend.model.Sex;
import com.example.spring_backend.model.Stroke;
import com.example.spring_backend.model.Swimmer;
import com.example.spring_backend.repository.DailyLogRepository;
import com.example.spring_backend.repository.SetRepository;
import com.example.spring_backend.repository.SwimmerRepository;

import jakarta.transaction.Transactional;

@Service
public class SanityCheck {
 private final SwimmerRepository sp;
    private final DailyLogRepository dlp;
    private final SetRepository sr;

    public SanityCheck(SwimmerRepository swimmerRepository,
                               DailyLogRepository dailyLogRepository,
                               SetRepository setRepository) {
       sp = swimmerRepository;
       dlp = dailyLogRepository;
        sr = setRepository;
    }
    @Transactional
 public void run(){
 
	Swimmer s = new Swimmer(20,Especiality.Sprint,"Shekoo Test","Shekoo123",Sex.Male,Stroke.SF);
     s= sp.save(s);

	 DailyLog log = new DailyLog(java.time.LocalDate.now(),SessionType.Swim,"ABC");
	 log.setSwimmer(s);
      log=    dlp.save(log);
	s.getDailyLog().add(log);

	  Set setObject = new Set(2,15,60,"Over 2",100);
     setObject.setDailyLog(log);   
	  setObject = sr.save(setObject);
     log.getSets().add(setObject);
    
	  Swimmer fetched = sp.findById(s.getId()).orElseThrow();
            System.out.println("Fetched swimmer: " + fetched.getName());
            System.out.println("DailyLogs: " + fetched.getDailyLog().size());
            System.out.println("Sets in first log: " + fetched.getDailyLog().get(0).getSets());     
	

}
}
