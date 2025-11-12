package com.dipak.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.dipak.entity.User;
import com.dipak.repository.UserRepository;
import com.dipak.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;
	 private final AuthenticationManager authManager;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        var dbUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid admin credentials"));

        String token = jwtUtil.generateToken(dbUser.getUsername(), dbUser.getRole());

        return ResponseEntity.ok("Bearer " + token);
    }
}
