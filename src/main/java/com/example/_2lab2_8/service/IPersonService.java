package com.example._2lab2_8.service;

import com.example._2lab2_8.entity.Person;

import java.util.List;
import java.util.Optional;

public interface IPersonService {

    Person findById(long id);
    Person add(Person person);
    Person delete(long id);
    Person edit(Person person);
    List<Person> getAll();

    Optional<Person> findByUsername(String username);
    Optional<Person> findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

}
