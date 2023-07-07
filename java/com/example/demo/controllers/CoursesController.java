package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;
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

import com.example.demo.entities.Courses;
import com.example.demo.repositories.CoursesRepository;

@RestController
@RequestMapping("/courses")
public class CoursesController {
	
	@Autowired
	private CoursesRepository c;
	
	@GetMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public @ResponseBody List<Courses> findAll() {
		return c.findAll();
	}
	
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public Courses save (@RequestBody Courses courses) {
		return c.save(courses);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Courses getById(@PathVariable UUID id) {
		return c.findById(id).orElseThrow(
				()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!"));
		
		 //Optional<Courses> cs = c.findById(id);
				//return cs.orElseThrow(
					//	()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!"));
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void delete (@PathVariable UUID id) {
		c.findById(id).map(course -> {c.delete(course);
		return course;})
		.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!"));
	}

	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void update (@PathVariable UUID id, @RequestBody Courses course) {
		c.findById(id).map(existsCourse -> {course.setId(existsCourse.getId());
		c.save(course);
		return existsCourse;}).orElseThrow(
				()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!"));
}
	
}
