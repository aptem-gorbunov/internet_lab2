package com.example._2lab2_8.service;

import com.example._2lab2_8.entity.Subject;

import java.util.List;
import java.util.Optional;

public interface ISubjectService {
    Subject findById(long id);
    Subject add(Subject subject);
    void delete(long id);
    Subject edit(Subject subject);
    List<Subject> getAll();
}
