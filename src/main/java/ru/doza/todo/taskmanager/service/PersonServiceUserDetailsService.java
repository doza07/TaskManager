package ru.doza.todo.taskmanager.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.doza.todo.taskmanager.models.Person;
import ru.doza.todo.taskmanager.repository.PersonRepository;
import ru.doza.todo.taskmanager.security.PersonDetailsImp;

import java.util.Optional;

@Component
public class PersonServiceUserDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    public PersonServiceUserDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> user = personRepository.findByUsername(username);

        if (user.isEmpty())
            throw new UsernameNotFoundException("Person " + username + " not found");

        return new PersonDetailsImp(user.get()) {
        };
    }
}
