package br.com.library.management.dto;


import jakarta.validation.constraints.NotBlank;

public record UserDto (
		@NotBlank(message= "Name is Required")     String name,
	    @NotBlank(message = "Email is required")   String email,
	    @NotBlank(message= "telephone is Required")String telephone){}

