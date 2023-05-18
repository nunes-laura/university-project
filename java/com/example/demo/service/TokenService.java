package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.entities.ClientUser;

@Service
public class TokenService {
	
	public String gerarToken(ClientUser user) {
		return JWT.create()
				.withIssuer("University")
				.withSubject(user.getUsername())
				.withExpiresAt(LocalDateTime.now().plusSeconds(60).toInstant(ZoneOffset.of("-03:00")))
				.sign(Algorithm.HMAC256("secret"));
	}

	public String getSubject(String token) {
		return JWT.require(Algorithm.HMAC256("secret"))
				.withIssuer("University")
				.build().verify(token)
				.getSubject();	
		}

}
