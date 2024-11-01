package edu.sdsu.appdev.springtutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<Task> getTasksByUserAndDate(CalendarPeeps user, LocalDate date) {
        return taskRepository.findByUserAndDate(user, date);
    }
}




