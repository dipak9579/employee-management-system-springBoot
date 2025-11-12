package com.dipak.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dipak.entity.User;
import com.dipak.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	private final UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepo.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));
		
		   return org.springframework.security.core.userdetails.User
	                .withUsername(user.getUsername())
	                .password(user.getPassword())
	                .roles(user.getRole()) 
	                .build();
	}
	
	
	

}
