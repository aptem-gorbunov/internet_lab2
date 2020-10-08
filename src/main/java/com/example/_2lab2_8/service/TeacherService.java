package com.example._2lab2_8.service;

import com.example._2lab2_8.entity.Teacher;
import com.example._2lab2_8.repository.ITeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService implements ITeacherService{

    private ITeacherRepository teacherRepository;

    @Autowired
    public void setTeacherRepository(ITeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Optional<Teacher> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Teacher add(Teacher teacher) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Teacher edit(Teacher teacher) {
        return null;
    }

    @Override
    public List<Teacher> getAll() {
        return null;
    }
}
