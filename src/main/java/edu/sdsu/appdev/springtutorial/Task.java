package edu.sdsu.appdev.springtutorial;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;


@Entity
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    private Long id;

    private String description;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private CalendarPeeps user;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public CalendarPeeps getUser() { return user; }
    public void setUser(CalendarPeeps user) { this.user = user; }

}
