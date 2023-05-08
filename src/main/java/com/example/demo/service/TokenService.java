package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.entities.UserEntity;

@Service
public class TokenService {

	public String generateToken(UserEntity user) {
		return JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(LocalDateTime.now()
						.plusMinutes(1)
						.toInstant(ZoneOffset.of("-03:00")))
				.sign(Algorithm.HMAC256("securitytest"));
				
	}

}
