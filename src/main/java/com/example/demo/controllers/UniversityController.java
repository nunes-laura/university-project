package com.example.demo.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.demo.entities.University;
import com.example.demo.repositories.UniversityRepository;

@RestController
@RequestMapping("/university")
public class UniversityController {
	
	@Autowired
	private UniversityRepository u;
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public @ResponseBody List<University> findAll(){
		return u.findAll(); }
	
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public University save(@RequestBody University uni) {
		return u.save(uni); }
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public University getById(@PathVariable UUID id) {
		return u.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "University not found!"));}

	
	@DeleteMapping("delete/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void delete (@PathVariable UUID id) {
		u.findById(id).map(uni ->{ u.delete(uni);
		return uni;})
		.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "University not found!"));}
	
	
	@PutMapping("update/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void update (@PathVariable UUID id, @RequestBody University university) {
		u.findById(id).map(existsUniversity -> {university.setId(existsUniversity.getId());
		u.save(university);
		return existsUniversity;})
		.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "University not found!"));}
		
	}
	


