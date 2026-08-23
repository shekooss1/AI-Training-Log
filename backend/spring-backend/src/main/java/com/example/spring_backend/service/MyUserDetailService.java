package com.example.spring_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.spring_backend.DTOs.SwimmerDTO;
import com.example.spring_backend.model.MyUserDetails;
import com.example.spring_backend.model.Swimmer;
import com.example.spring_backend.repository.SwimmerRepository;

@Service
public class MyUserDetailService implements UserDetailsService{
  
  private final SwimmerRepository repo ;


  MyUserDetailService(SwimmerRepository repo) {
    this.repo = repo;
  }

     
     @Override
     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      Swimmer swimmer = repo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Swimmer not found: " + email));
     
    return new MyUserDetails(swimmer);
    
    }
 

}
