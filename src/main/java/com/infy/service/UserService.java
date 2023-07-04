package com.infy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.infy.entity.User;
import com.infy.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User registerUser(String username, String password, String email) {
		User existingUser = userRepository.findByUsername(username);
		if (existingUser != null) {
			throw new IllegalArgumentException("Username already Present");
		}
		existingUser = userRepository.findByEmail(email);
		if (existingUser != null) {

			throw new IllegalArgumentException("Email is already registered");
		}
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setEmail(email);
		return userRepository.save(user);
	}

	public User loginUser(String username, String password) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new IllegalArgumentException("Invalid username");
		}

		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new IllegalArgumentException("Invalid password");
		}

		return user;
	}

	public User getUserById(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
	}



	public User getUserByUsername(String username) {
		// Retrieve the user by username from your data source
		Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(username));

		// Check if the user exists and return it, or return null if not found
		return userOptional.orElse(null);
	}


}
