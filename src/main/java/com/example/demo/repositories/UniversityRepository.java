package com.example.demo.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.University;

public interface UniversityRepository extends JpaRepository<University, UUID> {

	List<University> findAll();
}
