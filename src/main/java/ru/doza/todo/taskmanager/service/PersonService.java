package ru.doza.todo.taskmanager.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.doza.todo.taskmanager.models.Person;
import ru.doza.todo.taskmanager.models.Task;
import ru.doza.todo.taskmanager.repository.PersonRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);
    }

    public Optional<Person> findPersonByUsername(String userName) {
        return personRepository.findByUsername(userName);
    }

    public Optional <Person> findByUsername(String name) {
        return personRepository.findByUsername(name);
    }
}
