package com.gestao.biblioteca.recomendacoes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gestao.biblioteca.recomendacoes.dto.UserDto;
import com.gestao.biblioteca.recomendacoes.model.User;
import com.gestao.biblioteca.recomendacoes.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	
	@Transactional
	public User saveUser(UserDto userDto){
		User user = new User(userDto);
		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public Optional<User> getUserByID(Long uuid) {
		return userRepository.findById(uuid);
	}
	
	@Transactional
	public User updateUser(Long id, UserDto userDto) {
		return userRepository.findById(id)
  			   .map(existingUser-> updateExistingUser(existingUser,userDto))
 	          .orElseThrow(() -> new EntityNotFoundException("User not foun with ID: "+ id));
	
	}
	
	private User updateExistingUser(User existingUser,UserDto userDto) {
		existingUser.setName(userDto.name());
		existingUser.setEmail(userDto.email());
		existingUser.setTelephone(userDto.telephone());
		
		return userRepository.save(existingUser);
		
	}
	@Transactional
	public void deleteUserById(Long uuid) {
		userRepository.deleteById(uuid);
	}

	
	
	
}
