package com.example._2lab2_8.service;

import com.example._2lab2_8.entity.Student;
import com.example._2lab2_8.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface ITeacherService {
    Optional<Teacher> findById(long id);
    Teacher add(Teacher teacher);
    void delete(long id);
    //Student getByName(String name);
    Teacher edit(Teacher teacher);
    List<Teacher> getAll();
}
