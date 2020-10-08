package com.example._2lab2_8.service;

import com.example._2lab2_8.entity.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    Optional<Student> findById(long id);
    Student add(Student student);
    void delete(long id);
    //Student getByName(String name);
    Student edit(Student student);
    List<Student> getAll();
}
