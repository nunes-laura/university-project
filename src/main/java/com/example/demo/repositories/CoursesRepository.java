package com.example.demo.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Courses;

public interface CoursesRepository extends JpaRepository<Courses, UUID> {

	List<Courses> findAll();
}
