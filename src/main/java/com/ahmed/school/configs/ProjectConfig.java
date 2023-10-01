package com.ahmed.school.configs;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ProjectConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean(name = { "auditorAwareImpl" })
	public AuditorAware<String> auditorAwareImpl() {
		return new AuditorAware<String>() {
			@Override
			public Optional<String> getCurrentAuditor() {
				String email = SecurityContextHolder.getContext().getAuthentication().getName();
				return Optional.ofNullable(email);
			}
		};
	}
}
