package com.example.demo.entities;

import java.util.List;
import java.util.UUID;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "universitydata")
public class University {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private UUID id;
	
	@Column
	private String name;
	
	@Column
	private String CNPJ;
	
	@Column
	private String address;
	
	@OneToMany
	@JoinColumn(name = "university_id") //cria uma coluna na tabela de cursos
	private List<Courses> courses;

	public University(){};
	
	public University(UUID id, String name, String cNPJ, String address) {
		super();
		this.id = id;
		this.name = name;
		CNPJ = cNPJ;
		this.address = address;
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

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Courses> getCourses() {
		return courses;
	}

	public void setCourses(List<Courses> courses) {
		this.courses = courses;
	}
	
	

	
	
	


	
}
