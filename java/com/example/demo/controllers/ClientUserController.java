package com.example.demo.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.ClientUser;
import com.example.demo.repositories.ClientUserRepository;

@RestController
@RequestMapping("/client-users")
public class ClientUserController {
	
	@Autowired
	public ClientUserRepository userRepository;
	

	@Autowired
	private PasswordEncoder encoder;
	
	@GetMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public @ResponseBody List<ClientUser> findAll(){
		return userRepository.findAll(); }
	
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ClientUser save(@RequestBody ClientUser user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user); }
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void delete (@PathVariable UUID id) {
		userRepository.findById(id).map(user ->{ userRepository.delete(user);
		return user;})
		.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));}
	
	
	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void update (@PathVariable UUID id, @RequestBody ClientUser userEntity) {
		userRepository.findById(id).map(existsUser -> {userEntity.setId(existsUser.getId());
		userRepository.save(userEntity);
		return existsUser;})
		.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));}
	
}

