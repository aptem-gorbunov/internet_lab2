package com.example._2lab2_8.service;

import com.example._2lab2_8.entity.Person;
import com.example._2lab2_8.repository.PersonRepository;
import com.example._2lab2_8.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements IPersonService {

    private PersonRepository repository;

    @Autowired
    public void setRepository(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Person> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Person add(Person person) {
        return repository.saveAndFlush(person);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public Person edit(Person person) {
        return repository.saveAndFlush(person);
    }

    @Override
    public List<Person> getAll() {
        return repository.findAll();
    }
}
