package com.example._2lab2_8.service;

import com.example._2lab2_8.entity.Student;
import com.example._2lab2_8.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService{

    private StudentRepository repository;

    @Autowired
    public void setRepository(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Student> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Student add(Student student) {
        //TODO: flush, чтобы данные сразу попали в БД
        return repository.saveAndFlush(student);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

//    @Override
//    public Student getByName(String name) {
//        return studentRepository.findOne(st->)
//    }

    @Override
    public Student edit(Student student) {
        return repository.saveAndFlush(student);
    }

    @Override
    public List<Student> getAll() {
        return repository.findAll();
    }
}
