package com.gestao.biblioteca.recomendacoes.dto;


import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
public class BooksDto {

	@NotBlank(message= "title is Required")
	private String title;

	@NotBlank(message= "Autor is Required")
	private String author;
	
	@NotBlank(message= "isbn is Required")
	private String isbn;

	@NotBlank(message= "category is Required")
	private String category;
	
	@NotNull(message= "publishDate is Required")
	@Past(message=" The publication date must be earlier than what was entered")
	private Date publishDate;
}