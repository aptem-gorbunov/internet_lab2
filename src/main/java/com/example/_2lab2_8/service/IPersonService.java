package com.example._2lab2_8.service;

import com.example._2lab2_8.entity.Person;
import com.example._2lab2_8.entity.Student;

import java.util.List;
import java.util.Optional;

public interface IPersonService {
    Optional<Person> findById(long id);
    Person add(Person person);
    void delete(long id);
    Person edit(Person person);
    List<Person> getAll();
}
