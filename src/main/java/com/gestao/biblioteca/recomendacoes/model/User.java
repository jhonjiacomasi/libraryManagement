package com.gestao.biblioteca.recomendacoes.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestao.biblioteca.recomendacoes.dto.UserDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TB_USER")
@Data
public class User {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false, unique = true)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false, unique = true)
	private LocalDateTime registrationdate;

	@Column(nullable = false, unique = true)
	private String telephone;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	public Set<Loans> loans = new HashSet<>();

	
	public User() {
		
	}


	 public User(UserDto userDto) {
		super();
		this.id = UUID.randomUUID();
		this.name = userDto.getName();
		this.email = userDto.getEmail();
		this.registrationdate = LocalDateTime.now();
		this.telephone = userDto.getTelephone();
	}



	public User(UUID id, String name, String email, LocalDateTime registrationdate, String telephone,
			Set<Loans> loans) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.registrationdate = registrationdate;
		this.telephone = telephone;
		this.loans = loans;
	}


}