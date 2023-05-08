package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.UserEntityRepository;

@RestController
@RequestMapping("/users")
public class UserEntityController {
	
	@Autowired
	private UserEntityRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@GetMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public @ResponseBody List<UserEntity> findAll(){
		return userRepository.findAll(); }
	
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public UserEntity save(@RequestBody UserEntity user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user); }
	
	@DeleteMapping("delete/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void delete (@PathVariable UUID id) {
		userRepository.findById(id).map(user ->{ userRepository.delete(user);
		return user;})
		.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));}
	
	
	@PutMapping("update/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void update (@PathVariable UUID id, @RequestBody UserEntity userEntity) {
		userRepository.findById(id).map(existsUser -> {userEntity.setId(existsUser.getId());
		userRepository.save(userEntity);
		return existsUser;})
		.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));}
	
	
	@GetMapping("/validator")
	public boolean passwordValidator (@RequestParam String username, String password) {
		Optional<UserEntity> existsUser = Optional.ofNullable(userRepository.findByUsername(username));
		if (existsUser.isEmpty()) {
			return false;}
		
		UserEntity user = existsUser.get();
		boolean valid = encoder.matches(password, user.getPassword());
		
		return valid;
	}

}
