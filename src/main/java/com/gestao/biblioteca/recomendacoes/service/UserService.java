package com.gestao.biblioteca.recomendacoes.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.gestao.biblioteca.recomendacoes.dto.UserDto;
import com.gestao.biblioteca.recomendacoes.model.User;
import com.gestao.biblioteca.recomendacoes.repository.UserRepository;
import com.gestao.biblioteca.recomendacoes.utils.Util;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	
	@Transactional
	public User saveUser(@Valid UserDto userDto){
		
		if(!Util.isValid(userDto.getEmail())) {
			throw new IllegalArgumentException("Invalid Email: "+ userDto.getEmail());
		}
		User user = new User(userDto);
		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public Optional<User> getUserByID(UUID uuid) {
		return userRepository.findById(uuid);
	}
	
	@Transactional
	public User updateUser(UUID id, UserDto userDto) {
		return userRepository.findById(id)
  			   .map(existingUser-> updateExistingUser(existingUser,userDto))
 	          .orElseThrow(() -> new EntityNotFoundException("User not foun with ID: "+ id));
	
	}
	
	private User updateExistingUser(User existingUser,UserDto userDto) {
		existingUser.setName(userDto.getName());
		existingUser.setEmail(userDto.getEmail());
		existingUser.setTelephone(userDto.getTelephone());
		
		return userRepository.save(existingUser);
		
	}
	@Transactional
	public void deleteUserById(UUID uuid) {
		userRepository.deleteById(uuid);
	}

	
	
	
}
