package com.example._2lab2_8.controller;

import com.example._2lab2_8.aop.LogAnnotation;
import com.example._2lab2_8.entity.Subject;
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

    @LogAnnotation
    @GetMapping("/")
    List<Subject> getAll() {
        return service.getAll();
    }

    @LogAnnotation
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    Subject create(@Valid @RequestBody Subject newStudent) {
        return service.add(newStudent);
    }

    @LogAnnotation
    @GetMapping("/{id}")
    Subject getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @LogAnnotation
    @PutMapping("/{id}")
    Subject updateOne(@Valid @RequestBody Subject newSubjectInfo, @PathVariable Long id) {

        Subject subjectToUpdate = service.findById(id);
        subjectToUpdate.setName(newSubjectInfo.getName());
        return service.edit(subjectToUpdate);
    }

    @LogAnnotation
    @DeleteMapping("/{id}")
    void deleteStudent(@PathVariable Long id) {
        service.delete(id);
    }
}
