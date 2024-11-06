package edu.sdsu.appdev.springtutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import edu.sdsu.appdev.springtutorial.model.Users;
import org.springframework.http.ResponseEntity;
import edu.sdsu.appdev.springtutorial.service.UserService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UsersController {

	private final UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody Users user) {
		try {
			userService.registerUser(user);
			return ResponseEntity.ok("User registered successfully");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@RequestBody Users user, HttpSession session) {
		try {
			Users authenticatedUser = userService.authenticateUser(user.getUsername(), user.getPassword(), session);
			return ResponseEntity.ok("Login successful");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(401).body(e.getMessage());
		}
	}
}
