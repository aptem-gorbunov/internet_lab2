package com.example._2lab2_8.controller;

import com.example._2lab2_8.entity.Student;
import com.example._2lab2_8.repository.ITeacherRepository;
import com.example._2lab2_8.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("students")
public class StudentController {

    private StudentService service;

    StudentController(StudentService service) {
        this.service=service;
    }

    @GetMapping("/all")
    List<Student> getAllStudents() {
        return service.getAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    Student createStudent(@Valid @RequestBody Student newStudent) {
        return service.add(newStudent);
    }

    @GetMapping("/{id}")
    Optional<Student> getOneStudent(@PathVariable Long id) {
        return service.findById(id);
    }

    //TODO: не так, как должно было быть
    @PutMapping("/edit/{id}")
    Student updateStudent(@Valid @RequestBody Student updatedStudent, @PathVariable Long id) {
        return service.edit(updatedStudent);
    }

    @DeleteMapping("/{id}")
    void deleteStudent(@PathVariable Long id) {
        service.delete(id);
    }
}
