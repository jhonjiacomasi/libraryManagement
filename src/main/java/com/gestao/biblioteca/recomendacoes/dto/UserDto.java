package com.gestao.biblioteca.recomendacoes.dto;


import jakarta.validation.constraints.NotBlank;

public record UserDto (
		@NotBlank(message= "Name is Required")
	    String name,
	    
	    String email,
	    @NotBlank(message= "telephone is Required")
	    String telephone){}

