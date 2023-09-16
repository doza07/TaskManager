package ru.doza.todo.taskmanager.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username")
    @NotNull
    private String username;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "owner")
    private List<Task> tasks;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
