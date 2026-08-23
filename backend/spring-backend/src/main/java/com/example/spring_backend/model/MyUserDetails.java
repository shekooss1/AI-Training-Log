package com.example.spring_backend.model;

import java.util.Collection;
import java.util.Collections;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {
    private Swimmer swimmer ;

	public MyUserDetails(Swimmer swimmer){
		this.swimmer=swimmer;
	}
   
    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("SWIMMER"));
	}

	@Override
	public @Nullable String getPassword() {
	return swimmer.getPassword();
	}

	@Override
	public String getUsername() {
	return swimmer.getEmail();
	}

	@Override
	public boolean isEnabled() {
	return true ;
	}

	@Override
	public boolean isAccountNonExpired() {
	return true ;

	}

	@Override
	public boolean isAccountNonLocked() {
		return true ;

	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true ;

	}

	
}
