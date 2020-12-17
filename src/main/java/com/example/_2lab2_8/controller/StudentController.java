package com.example._2lab2_8.controller;

import com.example._2lab2_8.aop.LogAnnotation;
//import com.example._2lab2_8.service.StudentService;
import com.example._2lab2_8.entity.Mark;
import com.example._2lab2_8.entity.Person;
import com.example._2lab2_8.entity.dto.MainPersonInfoDto;
import com.example._2lab2_8.service.MarkService;
import com.example._2lab2_8.service.PersonService;
import com.example._2lab2_8.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
//
@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentService service;
    private final MarkService markService;

    StudentController(StudentService service, MarkService markService) {
        this.service=service;
        this.markService = markService;
    }

    @LogAnnotation
    @GetMapping("/")
    List<Person> getAllStudents() {
        List<Person> res = service.getAll();
        return res;
    }


    @LogAnnotation
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    //TODO: здесь нужна проверка поля
    Person createStudent(@Valid @RequestBody Person newStudent) {
        return service.add(newStudent);
    }

    @LogAnnotation
    @GetMapping("/{id}")
    //TDOO: здесь нужно получить Person, затем проверить значение поля
    Person getOneStudent(@PathVariable Long id) {
        return service.findById(id);
    }

    @LogAnnotation
    @PutMapping("/{id}")
    Person updateStudent(@Valid @RequestBody MainPersonInfoDto newInfo, @PathVariable Long id) {

        Person personForUpdate = service.findById(id);

        personForUpdate.setFirstName(newInfo.getFirstName());
        personForUpdate.setSurname(newInfo.getSurname());
        personForUpdate.setPatronymic(newInfo.getPatronymic());
        personForUpdate.setAge(newInfo.getAge());

        return service.edit(personForUpdate);
    }

    @LogAnnotation
    @GetMapping("/{id}/marks")
    ResponseEntity<List<Mark>> getGivenMarksByStudentId(@PathVariable Long id) {

        Person studentById = service.findById(id);

        List<Mark> studentMarks = markService.getMarksByStudent(studentById);
        return new ResponseEntity<>(studentMarks, HttpStatus.OK);
    }

    @LogAnnotation
    @DeleteMapping("/{id}")
    //TODO: тоже нужна првоерка, являтяли он студентом
    void deleteStudent(@PathVariable Long id) {
        service.delete(id);
    }
}
