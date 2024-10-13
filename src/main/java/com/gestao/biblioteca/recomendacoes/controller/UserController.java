package com.gestao.biblioteca.recomendacoes.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.biblioteca.recomendacoes.dto.UserDto;
import com.gestao.biblioteca.recomendacoes.model.User;
import com.gestao.biblioteca.recomendacoes.service.UserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService ;

	@GetMapping("/allUsers")
	public ResponseEntity<List<User>> getALLBooks() {

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.getALLBooks());
	}
	
	@GetMapping("/User/{id}")
	public ResponseEntity<User> getUserById(@PathVariable UUID id) {
	 Optional<User> user = userService.getUserByID(id);
		return ResponseEntity.ok(user.get());
	}
	
	@PostMapping("/saveUser")
	public ResponseEntity<User> saveUser(@RequestBody UserDto userDto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDto));
	}
	
}
