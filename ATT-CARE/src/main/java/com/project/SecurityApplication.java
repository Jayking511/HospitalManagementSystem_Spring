package com.project;

import com.project.auth.AuthenticationService;
import com.project.auth.RegisterRequest;
import com.project.model.Role;

import static com.project.model.Role.ADMIN;
import static com.project.model.Role.DOCTOR;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			/*
			 * var doctor = RegisterRequest.builder() .firstname("Admin") .lastname("Admin")
			 * .email("doctor@mail.com") .password("password") .role(DOCTOR) .build();
			 * System.out.println("Doctor token: " +
			 * service.register(doctor).getAccessToken());
			 */
		};
	}
}
