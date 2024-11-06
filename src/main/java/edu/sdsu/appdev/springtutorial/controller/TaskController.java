package edu.sdsu.appdev.springtutorial.controller;

import org.springframework.web.bind.annotation.*;

import edu.sdsu.appdev.springtutorial.model.Task;
import edu.sdsu.appdev.springtutorial.model.Users;
import edu.sdsu.appdev.springtutorial.repository.*;
import edu.sdsu.appdev.springtutorial.service.UserService;
import jakarta.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private final TaskRepository taskRepository;
	private final UserService userService;

	@Autowired
	public TaskController(TaskRepository taskRepository, UserService userService) {
		this.taskRepository = taskRepository;
		this.userService = userService;
	}

	@PostMapping("/create")
	public ResponseEntity<?> createTask(@RequestBody Task task, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			return ResponseEntity.status(401).body("User not logged in");
		}

		Users user = userService.findById(userId);
		task.setUsers(user); // Set the user as a foreign key in the task
		taskRepository.save(task);

		return ResponseEntity.ok("Task created successfully");
	}
}