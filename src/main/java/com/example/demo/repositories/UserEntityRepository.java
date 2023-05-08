package com.example.demo.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {
	
	List<UserEntity> findAll();
	
	UserEntity findByUsername(String username);

}
