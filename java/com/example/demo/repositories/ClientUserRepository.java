package com.example.demo.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.ClientUser;

@Repository
public interface ClientUserRepository extends JpaRepository<ClientUser, UUID> {
	
	List<ClientUser> findAll();
	
	//Optional<ClientUser> findByPassword(String password); //validator
	
	ClientUser findByLogin(String username); //userdetails

}
