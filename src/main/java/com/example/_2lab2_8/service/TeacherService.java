package com.example._2lab2_8.service;

import com.example._2lab2_8.entity.Person;
import com.example._2lab2_8.entity.Role;
import com.example._2lab2_8.exception.PersonIsNotTeacher;
import com.example._2lab2_8.exception.PersonNotFound;
import com.example._2lab2_8.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService implements ITeacherService{

    private PersonRepository repository;

    @Autowired
    public void setRepository(PersonRepository repository) {
        this.repository = repository;
    }


    @Override
    public Person findById(long id) {
        Person personById = repository.findById(id).orElseThrow(PersonNotFound::new);
        if (personById.getRole() != Role.TEACHER) {
            throw new PersonIsNotTeacher();
        }
        else {
            return personById;
        }
    }

    @Override
    public Person add(Person person) {
        person.setRole(Role.TEACHER);
        return repository.saveAndFlush(person);
    }

    @Override
    public Person delete(long id) {

        Person personById = repository.findById(id).orElseThrow(PersonNotFound::new);

        if (personById.getRole() != Role.TEACHER) {
            throw new PersonIsNotTeacher();
        }
        else {
            repository.deleteById(id);
            return personById;
        }

    }

    @Override
    public Person edit(Person person) {
        person.setRole(Role.TEACHER);
        return repository.saveAndFlush(person);
    }

    @Override
    public List<Person> getAll() {
        return repository.findAll().stream()
                .filter(person -> person.getRole() == Role.TEACHER)
                .collect(Collectors.toList());
    }

}
