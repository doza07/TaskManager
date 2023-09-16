package ru.doza.todo.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.doza.todo.taskmanager.models.Person;
import ru.doza.todo.taskmanager.models.Task;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<Task, Integer> {
    List<Task> findByCasesStartingWith(String cases);
    List<Task> findByOwner(Person owner);
    Task findByIdAndOwner(int id, Person owner);
}
