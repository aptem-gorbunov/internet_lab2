package com.example._2lab2_8.controller;

import com.example._2lab2_8.entity.Person;
import com.example._2lab2_8.entity.Student;
import com.example._2lab2_8.service.PersonService;
import com.example._2lab2_8.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("people")
public class PersonController {
    private final PersonService service;

    PersonController(PersonService service) {
        this.service=service;
    }

    @GetMapping("/all")
    List<Person> getAllStudents() {
        return service.getAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    Person createStudent(@Valid @RequestBody Person newStudent) {
        return service.add(newStudent);
    }

    @GetMapping("/{id}")
    Optional<Person> getOneStudent(@PathVariable Long id) {
        return service.findById(id);
    }

    //TODO: не так, как должно было быть
    @PutMapping("/edit/{id}")
    Person updateStudent(@Valid @RequestBody Person updatedStudent, @PathVariable Long id) {
        return service.edit(updatedStudent);
    }

    @DeleteMapping("/{id}")
    void deleteStudent(@PathVariable Long id) {
        service.delete(id);
    }
}
