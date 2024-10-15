package br.com.library.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.library.management.dto.UserDto;
import br.com.library.management.exception.LibraryManagementException;
import br.com.library.management.model.User;
import br.com.library.management.repository.UserRepository;
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
		try {
			User user = new User(userDto);
			return userRepository.save(user);
		}catch (Exception e) {
			throw new LibraryManagementException("Failed to save user: " + userDto.name(), e);
		}
	}

	public List<User> getAllUsers() {
		try {
			return userRepository.findAll();
		} catch (Exception e) {
			throw new LibraryManagementException("Failed fecth to Users from Database : " + e);
		}

	}
	
	public Optional<User> getUserByID(Long uuid) {
		try {
			return userRepository.findById(uuid);
		}catch (Exception e) {
			throw new LibraryManagementException("Failed fecth to User from Database " + uuid, e);
		}
		
	}
	
	@Transactional
	public User updateUser(Long id, UserDto userDto) {
		 		return userRepository.findById(id)
		        .map(existingUser -> updateExistingUser(existingUser, userDto))
		        .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));

	}
	private User updateExistingUser(User existingUser, UserDto userDto) {
		existingUser.setName(userDto.name());
		existingUser.setEmail(userDto.email());
		existingUser.setTelephone(userDto.telephone());

		return userRepository.save(existingUser);

	}

	@Transactional
	public void deleteUserById(Long uuid) {
		try {
			userRepository.deleteById(uuid);
		} catch (Exception e) {
			throw new LibraryManagementException("Failed to Delete to user in Database : " + uuid, e);
		}

	}

}
