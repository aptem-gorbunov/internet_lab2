package com.example._2lab2_8.service;

import com.example._2lab2_8.entity.Person;
import com.example._2lab2_8.exception.CannotFindPersonByEmail;
import com.example._2lab2_8.exception.CannotFindPersonByUsername;
import com.example._2lab2_8.exception.PersonNotFound;
import com.example._2lab2_8.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements IPersonService, UserDetailsService {

    private PersonRepository repository;

    @Autowired
    public void setRepository(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person findById(long id) {
        return repository.findById(id).orElseThrow(PersonNotFound::new);
    }

    @Override
    public Person add(Person person) {
        return repository.saveAndFlush(person);
    }

    @Override
    public Person delete(long id) {
        Person personById = repository.findById(id).orElseThrow(PersonNotFound::new);
        repository.deleteById(id);
        return personById;
    }

    @Override
    public Person edit(Person person) {
        return repository.saveAndFlush(person);
    }

    @Override
    public List<Person> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Person> findByUsername(String username) {

        return repository.getPersonByUsername(username);
    }

    @Override
    public Optional<Person> findByEmail(String email) {

        return repository.getPersonByEmail(email);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {



        return null;//new User()
    }
}
