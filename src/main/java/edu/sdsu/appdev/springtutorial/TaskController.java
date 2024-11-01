package edu.sdsu.appdev.springtutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserRepository userRepository;

    // Endpoint to add a new task
    @PostMapping("/{userId}")
    public Task addTask(@PathVariable Integer userId, @RequestBody Task task) {
        CalendarPeeps user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        task.setUser(user);
        return taskService.addTask(task);
    }

    // Endpoint to get tasks by user and date
    @GetMapping("/{userId}")
    public List<Task> getTasksByUserAndDate(@PathVariable Integer userId, @RequestParam String date) {
        CalendarPeeps user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        LocalDate taskDate = LocalDate.parse(date);
        return taskService.getTasksByUserAndDate(user, taskDate);
    }
}

