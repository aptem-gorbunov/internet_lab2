package com.example._2lab2_8.service;

import com.example._2lab2_8.entity.Teacher;
import com.example._2lab2_8.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService implements ITeacherService{

    private TeacherRepository repository;

    @Autowired
    public void setRepository(TeacherRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Teacher> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Teacher add(Teacher teacher) {
        return repository.saveAndFlush(teacher);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public Teacher edit(Teacher teacher) {
        return repository.saveAndFlush(teacher);
    }

    @Override
    public List<Teacher> getAll() {
        return repository.findAll();
    }
}
