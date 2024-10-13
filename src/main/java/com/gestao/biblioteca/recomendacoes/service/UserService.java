package com.gestao.biblioteca.recomendacoes.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.gestao.biblioteca.recomendacoes.dto.UserDto;
import com.gestao.biblioteca.recomendacoes.model.User;
import com.gestao.biblioteca.recomendacoes.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	private final UserRepository userRepository;
	
	@Transactional
	public User saveUser(UserDto userDto){
		User user = new User(userDto);
		return userRepository.save(user);
	}

	public List<User> getALLBooks() {
		return userRepository.findAll();
	}
	
	public Optional<User> getUserByID(UUID uuid) {
		return userRepository.findById(uuid);
	}

}
