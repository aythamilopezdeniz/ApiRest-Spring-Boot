package com.backendclients.model.entity;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message = "Nombre no puede ser vacío.")
	@Column(name = "name", length = 60)
	private String name;

	@NotEmpty(message = "Apellido no puede ser vacío.")
	@Column(name = "surname", length = 60)
	private String surname;
	
	@NotEmpty(message = "Edad no puede ser vacío.")
	@Column(name = "age")
	private int age;

	@NotEmpty(message = "Email no puede ser vacío.")
	@Column(name = "email", length = 200)
	@Email(message = "Email debería ser un email válido.")
	private String email;

	public Client() {
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}