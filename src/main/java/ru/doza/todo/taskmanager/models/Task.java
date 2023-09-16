package ru.doza.todo.taskmanager.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;


@Entity(name = "todo")
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "cases")
    @NotNull
    private String cases;
    @Column(name = "description")
    private String description;
    @Column(name = "priority")
    private Priority priority;
    @Column(name = "status")
    @NotNull
    private Status status;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person owner;

    public enum Priority {
        Low, Medium, High
    }

    public enum Status {
        ToDo, Process, Done
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public Status getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}