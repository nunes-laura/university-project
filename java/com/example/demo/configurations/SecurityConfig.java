package com.example.demo.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.components.FilterToken;


@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private FilterToken filter;
	
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeHttpRequests()
				.antMatchers(HttpMethod.POST, "/client-users/save", "/login")
				.permitAll()
				.antMatchers(HttpMethod.GET, "/home")
				.permitAll()
				.antMatchers(HttpMethod.DELETE, "/courses/**", "/university/**", "/client-users/**")
				.hasAuthority("ADMIN")
				.antMatchers("/university/**", "/client-users/**", "/courses/**")
				.authenticated()
				.and()
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	

}
