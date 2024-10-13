package com.gestao.biblioteca.recomendacoes.model;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "TB_LOANS")
public class Loans {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "books_id")
	private Books books;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private Date loansDate;

	@Column(nullable = false)
	private Date returnDate;

	@Column(nullable = false)
	private Boolean status;

}
