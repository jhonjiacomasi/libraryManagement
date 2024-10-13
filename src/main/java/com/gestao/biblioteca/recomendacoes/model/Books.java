package com.gestao.biblioteca.recomendacoes.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_BOOKS")
public class Books {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false, unique = true)
	private String title;

	@Column(nullable = false, unique = true)
	private String author;

	@Column(nullable = false, unique = true)
	private String isbn;

	@Column(nullable = false)
	private Date publishDate;

	@Column(nullable = false)
	private String category;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "books", fetch = FetchType.LAZY)
	public Set<Loans> loans = new HashSet<>();

}
