package com.gestao.biblioteca.recomendacoes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.biblioteca.recomendacoes.dto.UserDto;
import com.gestao.biblioteca.recomendacoes.model.User;
import com.gestao.biblioteca.recomendacoes.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/allUsers")
	public ResponseEntity<List<User>> getALLBooks() {

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.getAllUsers());
	}

	@GetMapping("/UserById/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		Optional<User> user = userService.getUserByID(id);
		return ResponseEntity.ok(user.get());
	}

	@PostMapping("/saveUser")
	public ResponseEntity<User> saveUser(@RequestBody UserDto userDto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDto));
	}
	
	@PutMapping("/updateUser/{uuid}")
	public ResponseEntity<User> updateUser(@PathVariable("uuid") Long uuid, @RequestBody @Valid UserDto userDto){
		try {
			User updateUser = userService.updateUser(uuid, userDto);
			return ResponseEntity.ok(updateUser);
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
	}

	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		userService.deleteUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body("User delete sucessfull");
	}

}
