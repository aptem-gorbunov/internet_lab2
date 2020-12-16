package com.example._2lab2_8.service;

import com.example._2lab2_8.entity.Subject;
import com.example._2lab2_8.exception.SubjectNotFound;
import com.example._2lab2_8.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService implements ISubjectService{

    private SubjectRepository repository;

    @Autowired
    public void setRepository(SubjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Subject findById(long id) {
        return repository.findById(id).orElseThrow(SubjectNotFound::new);
    }

    @Override
    public Subject add(Subject subject) {
        return repository.saveAndFlush(subject);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public Subject edit(Subject subject) {
        return repository.saveAndFlush(subject);
    }

    @Override
    public List<Subject> getAll() {
        return repository.findAll();
    }
}
