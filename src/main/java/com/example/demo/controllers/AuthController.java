package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Login;
import com.example.demo.entities.UserEntity;
import com.example.demo.service.TokenService;

@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public String save(@RequestBody Login login) {
		UsernamePasswordAuthenticationToken auth =	
				new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
		
		Authentication authenticate = this.authManager.authenticate(auth);
		 
		var user = (UserEntity)authenticate.getPrincipal();
		
		var token = tokenService.generateToken(user);
		
		return token ;
	}

}
