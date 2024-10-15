package br.com.library.management.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record LoansDto	(
		@NotBlank(message= "user_id is Required")    Long user_id,
		@NotBlank(message= "books_id is Required")   UUID books_id,
		@NotBlank(message= "books_id is Required")   String loansDate,
		@NotBlank(message= "returnDate is Required") String returnDate,
		@NotBlank(message= "status is Required")     Boolean status){}