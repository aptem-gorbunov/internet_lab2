package com.example._2lab2_8.controller;

import com.example._2lab2_8.entity.Student;
import com.example._2lab2_8.entity.Teacher;
import com.example._2lab2_8.service.StudentService;
import com.example._2lab2_8.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("teachers")
public class TeacherController {

    //TODO: можно было бы просто 1 контроллер сделать, от которого бы другие наследовались (понятное дело, что универсально назвать методы основные)

    private TeacherService service;

    TeacherController(TeacherService service) {
        this.service=service;
    }

    @GetMapping("/all")
    List<Teacher> getAllTeachers() {
        return service.getAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    Teacher createTeacher(@Valid @RequestBody Teacher newTeacher) {
        return service.add(newTeacher);
    }

    @GetMapping("/{id}")
    Optional<Teacher> getOneTeacher(@PathVariable Long id) {
        return service.findById(id);
    }

    //TODO: не так, как должно было быть
    @PutMapping("/edit/{id}")
    Teacher updateTeacher(@Valid @RequestBody Teacher updatedTeacher, @PathVariable Long id) {
        return service.edit(updatedTeacher);
    }

    @DeleteMapping("/{id}")
    void deleteStudent(@PathVariable Long id) {
        service.delete(id);
    }
}
