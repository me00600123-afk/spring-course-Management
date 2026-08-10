package com.global.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.global.filter.JwtFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	@Autowired
	JwtFilter jwtFilter;
	
	
	@Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity http) {
		return http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.csrf(crsf->crsf.disable())
				.authorizeHttpRequests(auth->auth.requestMatchers("/auth/login","/auth/refresh").permitAll()
						.anyRequest()
						.authenticated())
				.build();
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
		return configuration.getAuthenticationManager();
	}
	
	

}
