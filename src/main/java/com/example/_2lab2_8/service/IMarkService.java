package com.example._2lab2_8.service;

import com.example._2lab2_8.entity.Mark;
import com.example._2lab2_8.entity.Person;

import java.util.List;

public interface IMarkService {

    Mark findById(long id);
    Mark add(Mark person);
    Mark delete(long id);
    Mark edit(Mark person);
    List<Mark> getAll();

    List<Mark> getMarksByTeacher(Person teacher);
    List<Mark> getMarksByStudent(Person teacher);

}
