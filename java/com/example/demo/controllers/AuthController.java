package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Login;
import com.example.demo.entities.ClientUser;
import com.example.demo.service.TokenService;

@RestController
public class AuthController {
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	TokenService tokenService;
	
	@PostMapping("/login")
	public String login (@RequestBody Login login) {
		
		UsernamePasswordAuthenticationToken authToken = 
				new UsernamePasswordAuthenticationToken(login.login(), login.password());
		
		Authentication authentication = this.authManager.authenticate(authToken);
		
		var usuario = (ClientUser)authentication.getPrincipal();
		
		
		return tokenService.gerarToken(usuario);
	}
	

}
