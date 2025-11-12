package com.dipak;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dipak.entity.User;
import com.dipak.repository.UserRepository;

@SpringBootApplication
public class EmployeeManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}

	   @Bean
	   CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
	        return args -> {
	            if (userRepository.findByUsername("admin").isEmpty()) {
	                User admin = User.builder()
	                        .username("admin")
	                        .password(passwordEncoder.encode("admin123"))
	                        .role("ADMIN")
	                        .build();
	                userRepository.save(admin);
	                System.out.println("✅ Admin user created: admin / admin123");
	            }
	        };
	    }
}
