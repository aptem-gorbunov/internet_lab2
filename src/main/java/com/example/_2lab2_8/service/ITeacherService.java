package com.example._2lab2_8.service;

import com.example._2lab2_8.entity.Person;

import java.util.List;

public interface ITeacherService {

    Person findById(long id);
    Person add(Person person);
    Person delete(long id);
    Person edit(Person person);
    List<Person> getAll();

}
