package com.example._2lab2_8.controller;

import com.example._2lab2_8.entity.Student;
import com.example._2lab2_8.entity.Subject;
import com.example._2lab2_8.service.StudentService;
import com.example._2lab2_8.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("subjects")
public class SubjectController {
    private final SubjectService service;

    SubjectController(SubjectService service) {
        this.service=service;
    }

    @GetMapping("/all")
    List<Subject> getAll() {
        return service.getAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    Subject create(@Valid @RequestBody Subject newStudent) {
        return service.add(newStudent);
    }

    @GetMapping("/{id}")
    Optional<Subject> getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    //TODO: !!!не так, как должно было быть (надо как-то использовать id (а, может быть, и не нужно))
    @PutMapping("/edit/{id}")
    Subject updateOne(@Valid @RequestBody Subject updatedStudent, @PathVariable Long id) {
        return service.edit(updatedStudent);
    }

    @DeleteMapping("/{id}")
    void deleteStudent(@PathVariable Long id) {
        service.delete(id);
    }
}
