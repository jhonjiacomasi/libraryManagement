package com.gestao.biblioteca.recomendacoes.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {

	@NotBlank(message= "Name is Required")
	private String name;

	@NotBlank(message= "Name is Required")
	@Email(message= "Email is invalid")
	private String email;

	@NotBlank(message= "telephone is Required")
	private String telephone;
}
