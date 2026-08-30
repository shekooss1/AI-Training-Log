package com.example.spring_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.spring_backend.service.MyUserDetailService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
   MyUserDetailService userDetailsService ;

@Autowired
JwtFilter jwtFilter ;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

   return http
    .csrf(cs -> cs.disable())
    .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST,"/api/swimmers","/login").permitAll().anyRequest().authenticated())
    .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
           .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
           .build() ;
 }

@Bean
 public AuthenticationProvider authProvider(){
    DaoAuthenticationProvider pro = new DaoAuthenticationProvider(userDetailsService);
    pro.setPasswordEncoder(new BCryptPasswordEncoder(12));
    return pro ;
 }

@Bean
  public AuthenticationManager authManager(AuthenticationConfiguration authenticationConfiguration){
        return authenticationConfiguration.getAuthenticationManager();
}
}
