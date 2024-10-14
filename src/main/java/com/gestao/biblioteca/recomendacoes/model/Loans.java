package com.gestao.biblioteca.recomendacoes.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.gestao.biblioteca.recomendacoes.dto.LoansDto;
import com.gestao.biblioteca.recomendacoes.utils.Util;

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

	@Column(nullable = false)
	private String loansDate;

	@Column(nullable = false)
	private String returnDate;

	@Column(nullable = false)
	private Boolean status;

	public Loans(LoansDto loansDto) {
		this.id = UUID.randomUUID();
		this.loansDate = Util.formatDate(LocalDateTime.now());
		this.returnDate = Util.formatDate(LocalDateTime.now().plusWeeks(2));
		this.status = loansDto.status();

	}

	public Loans() {}
}
