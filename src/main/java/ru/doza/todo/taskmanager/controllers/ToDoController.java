package ru.doza.todo.taskmanager.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.doza.todo.taskmanager.models.Person;
import ru.doza.todo.taskmanager.models.Task;
import ru.doza.todo.taskmanager.service.PersonService;
import ru.doza.todo.taskmanager.service.TaskService;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/todo")
public class ToDoController {

    private final TaskService taskService;
    private final PersonService personService;

    @Autowired
    public ToDoController(TaskService taskService, PersonService personService) {
        this.taskService = taskService;
        this.personService = personService;
    }

    @GetMapping("/all_task")
    public String viewAllTaskList(Model model, Principal principal) {
        Person currentUser = personService.findByUsername(principal.getName()).orElse(null);
        List<Task> userTasks = taskService.findAllTasksByOwner(currentUser);
        model.addAttribute("todo", userTasks);
        return "todo/all_task";
    }

    @GetMapping("/{id}")
    public String showTask(@PathVariable("id") int id, Model model) {
        model.addAttribute("todo", taskService.findOne(id));
        return "todo/show";

    }

    @GetMapping("/new")
    public String newTask(@ModelAttribute("todo") Task toDo) {
        return "todo/new";
    }


    @PostMapping
    public String createTask(@ModelAttribute("todo") @Valid Task toDo,
                             BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors())
            return "todo/new";
        Person owner = personService.findPersonByUsername(principal.getName()).orElse(null);
        toDo.setOwner(owner);
        taskService.save(toDo);
        return "redirect:todo/all_task";
    }

    @GetMapping("/{id}/edit")
    public String editTask(Model model, @PathVariable("id") int id) {
        model.addAttribute("todo", taskService.findOne(id));
        return "todo/edit";
    }


    @PostMapping("/{id}/edit")
    public String updateTask(@ModelAttribute("todo")
                             @Valid Task updateToDo, BindingResult bindingResult,
                             @PathVariable("id") int id,
                                Principal principal) {
        if (bindingResult.hasErrors())
            return "todo/edit";
        Person owner = personService.findPersonByUsername(principal.getName()).orElse(null);
        updateToDo.setOwner(owner);
        taskService.update(id, updateToDo);
        return "redirect:http://localhost:8080/todo/all_task";
    }


    @PostMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        taskService.delete(id);
        return "redirect:http://localhost:8080/todo/all_task";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "todo/search";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam("query") String query) {
        model.addAttribute("todo", taskService.searchByTitle(query));
        return "todo/search";
    }

}


