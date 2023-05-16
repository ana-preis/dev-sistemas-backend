package com.incendiosflorestais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class IncendiosFlorestaisApplication {

	public static void main(String[] args) {
		SpringApplication.run(IncendiosFlorestaisApplication.class, args);
	}

}
