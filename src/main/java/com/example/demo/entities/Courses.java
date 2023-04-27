package com.example.demo.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.enums.Categories;

@Entity
@Table(name = "courses")
public class Courses {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private UUID id;
	
	@Column
	private String name;
	
	@Column
	private Integer code;
	
	@Enumerated(EnumType.STRING)
	@Column
	private Categories category;
	
	
	
	public Courses(){};

	public Courses(UUID id, String name, Categories category, Integer code) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.code = code;	
		
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}


}
