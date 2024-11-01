package edu.sdsu.appdev.springtutorial;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    List<Task> findByUserAndDate(CalendarPeeps user, LocalDate date);
}
