package edu.sdsu.appdev.springtutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sdsu.appdev.springtutorial.model.Users;
import edu.sdsu.appdev.springtutorial.model.Task;
import edu.sdsu.appdev.springtutorial.repository.TaskRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Transactional
	public Task addTask(Task task) {
		return taskRepository.save(task);
	}

}
