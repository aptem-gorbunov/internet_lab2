package com.example._2lab2_8.service;

import com.example._2lab2_8.entity.Mark;
import com.example._2lab2_8.entity.Person;
import com.example._2lab2_8.exception.MarkNotFound;
import com.example._2lab2_8.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkService implements IMarkService{

    private MarkRepository repository;

    @Autowired
    public void setRepository(MarkRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mark findById(long id) {

        return repository.findById(id).orElseThrow(MarkNotFound::new);
    }

    @Override
    public Mark add(Mark person) {
        return repository.saveAndFlush(person);
    }

    @Override
    public Mark delete(long id) {
        Mark markById = repository.findById(id).orElseThrow(MarkNotFound::new);
        repository.deleteById(id);
        return markById;
    }

    @Override
    public Mark edit(Mark person) {
        return repository.saveAndFlush(person);
    }

    @Override
    public List<Mark> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Mark> getMarksByTeacher(Person teacher) {
        return repository.getMarksByTeacher(teacher);
    }

    @Override
    public List<Mark> getMarksByStudent(Person teacher) {
        return repository.getMarksByStudent(teacher);
    }
}
