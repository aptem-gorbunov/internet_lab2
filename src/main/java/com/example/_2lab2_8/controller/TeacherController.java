package com.example._2lab2_8.controller;

import com.example._2lab2_8.aop.LogAnnotation;
//import com.example._2lab2_8.service.TeacherService;
import com.example._2lab2_8.entity.Mark;
import com.example._2lab2_8.entity.Person;
import com.example._2lab2_8.entity.dto.MainPersonInfoDto;
import com.example._2lab2_8.service.MarkService;
import com.example._2lab2_8.service.PersonService;
import com.example._2lab2_8.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
//
@RestController
@RequestMapping("teachers")
public class TeacherController {

    //TODO: можно было бы просто 1 контроллер сделать, от которого бы другие наследовались (понятное дело, что универсально назвать методы основные)

    private final TeacherService service;
    private final MarkService markService;

    TeacherController(TeacherService service, MarkService markService) {
        this.service=service;
        this.markService = markService;
    }

    @LogAnnotation
    @GetMapping("/")
    List<Person> getAllTeachers() {
        return service.getAll();
    }

    @LogAnnotation
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    Person createTeacher(@Valid @RequestBody Person newTeacher) {
        return service.add(newTeacher);
    }

    @LogAnnotation
    @GetMapping("/{id}")
    Person getOneTeacher(@PathVariable Long id) {
        return service.findById(id);
    }

    @LogAnnotation
    @PutMapping("/{id}")
    Person updateTeacher(@Valid @RequestBody MainPersonInfoDto newInfo, @PathVariable Long id) {
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

        Person teacherById = service.findById(id);

        List<Mark> teacherGivenMarks = markService.getMarksByTeacher(teacherById);
        return new ResponseEntity<>(teacherGivenMarks, HttpStatus.OK);
    }

    @LogAnnotation
    @DeleteMapping("/{id}")
    void deleteStudent(@PathVariable Long id) {
        service.delete(id);
    }
}
