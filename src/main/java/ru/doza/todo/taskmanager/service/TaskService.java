package ru.doza.todo.taskmanager.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.doza.todo.taskmanager.models.Person;
import ru.doza.todo.taskmanager.models.Task;
import ru.doza.todo.taskmanager.repository.ToDoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final ToDoRepository toDoRepository;

    @Autowired
    public TaskService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<Task> findAll() {
        return toDoRepository.findAll();
    }

    public Task findOne(int id) {
        Optional<Task> findTask = toDoRepository.findById(id);
        return findTask.orElse(null);
    }

    public void save(Task toDo) {
        toDo.setCreatedAt(LocalDateTime.now());
        toDoRepository.save(toDo);
    }

    public void update(int id, Task updateToDo) {
        updateToDo.setId(id);
        updateToDo.setLastUpdate(LocalDateTime.now());
        toDoRepository.save(updateToDo);
    }

    public void delete(int id) {
        toDoRepository.deleteById(id);
    }

    public List<Task> searchByTitle(String query) {
        return toDoRepository.findByCasesStartingWith(query);
    }

    public List<Task> findAllTasksByOwner(Person owner) {
        return toDoRepository.findByOwner(owner);
    }
    public Task findOneTaskByIdAndOwner(int id, Person owner) {
        return toDoRepository.findByIdAndOwner(id, owner);
    }
}
