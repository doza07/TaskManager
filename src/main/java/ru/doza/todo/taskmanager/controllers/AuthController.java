package ru.doza.todo.taskmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.doza.todo.taskmanager.models.Person;
import ru.doza.todo.taskmanager.service.PersonService;
import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PersonService personService;

    @Autowired
    public AuthController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/login")
        public String loginPage() {
            return "auth/login";
        }


    @GetMapping("/registration")
        public String registrationPage(@ModelAttribute("person") Person person) {
            return "auth/registration";
        }

    @PostMapping("/registration")
    public String registration(@Valid Person person,
                                                   BindingResult bindingResult) {
        personService.register(person);
        return "redirect:http://localhost:8080/auth/login";
    }
}
