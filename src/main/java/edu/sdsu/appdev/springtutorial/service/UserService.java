package edu.sdsu.appdev.springtutorial.service;

import org.springframework.stereotype.Service;

import edu.sdsu.appdev.springtutorial.repository.UsersRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import edu.sdsu.appdev.springtutorial.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
public class UserService {

	private final UsersRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public UserService(UsersRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public Users registerUser(Users user) {
		// Check if username already exists
		if (userRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new IllegalArgumentException("Username is already taken");
		}
		// Encode password and save user
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public Users authenticateUser(String username, String password, HttpSession session) {
		Users user = userRepository.findByUsername(username)
				.orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

		// Use matches to compare encoded password
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new IllegalArgumentException("Invalid credentials");
		}

		// Store user ID in session if credentials match
		session.setAttribute("userId", user.getId());
		return user;
	}

	public Users findById(Integer userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("User not found"));
	}

}
