package com.blogging_platform.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain config(HttpSecurity http) throws Exception{
		http.csrf().disable()
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/api/admin/**").hasRole("ADMIN")
					.requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
					.anyRequest().authenticated())
			.httpBasic();
		
		return http.build();
	}
	
	
}
