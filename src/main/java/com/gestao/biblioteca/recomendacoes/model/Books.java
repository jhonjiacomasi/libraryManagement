package com.gestao.biblioteca.recomendacoes.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestao.biblioteca.recomendacoes.dto.BooksDto;

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
public class Books implements Serializable{
	private static final long serialVersionUID = 1L;

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
	
	
	
	 public Books(BooksDto bookDto) {
			super();
			this.id = UUID.randomUUID();
			this.title = bookDto.title();
			this.author = bookDto.author();
			this.isbn = bookDto.isbn();
			this.publishDate = bookDto.publishDate();
			this.category = bookDto.category();
			
		}
	 


	 public Books(UUID id, String title, String author, String isbn, Date publishDate, String category,
				Set<Loans> loans) {
			super();
			this.id = id;
			this.title = title;
			this.author = author;
			this.isbn = isbn;
			this.publishDate = publishDate;
			this.category = category;
			this.loans = loans;
		}


	public Books() {
		super();
	}





	

}
