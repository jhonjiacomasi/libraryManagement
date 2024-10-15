package br.com.library.management.dto;


import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record BooksDto (@NotBlank(message= "title is Required") String title,
						@NotBlank(message= "Autor is Required")String author,
						@NotBlank(message= "isbn is Required")String isbn,
						@NotBlank(message= "category is Required")String category,
						@NotNull(message= "publishDate is Required")
						@Past(message=" The publication date must be earlier than what was entered")
						Date publishDate) {}

	