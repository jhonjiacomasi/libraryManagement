package com.gestao.biblioteca.recomendacoes.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestao.biblioteca.recomendacoes.dto.UserDto;
import com.gestao.biblioteca.recomendacoes.utils.Util;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity
@Table(name = "TB_USER")
@Data
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	@Column(nullable = false)
	@Email(message = "Email is invalid")
	private String email;

	@Column(nullable = false)
	private String registrationdate;

	@Column(nullable = false, unique = true)
	private String telephone;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	public Set<Loans> loans = new HashSet<>();

	
	public User() {}

	private Long generateReducedUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.getMostSignificantBits();
	}

	 public User(UserDto userDto) {
		super();
		this.id = generateReducedUUID();
		this.name = userDto.name();
		this.email = userDto.email();
		this.registrationdate = Util.formatDate(LocalDateTime.now());
		this.telephone = userDto.telephone();
	}


}