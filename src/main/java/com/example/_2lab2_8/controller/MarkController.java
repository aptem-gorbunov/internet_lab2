package com.example._2lab2_8.controller;

import com.example._2lab2_8.aop.LogAnnotation;
import com.example._2lab2_8.entity.Mark;
import com.example._2lab2_8.entity.Person;
import com.example._2lab2_8.entity.Subject;
import com.example._2lab2_8.entity.dto.MarkDto;
import com.example._2lab2_8.entity.dto.MarkValueDto;
import com.example._2lab2_8.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("marks")
//TODO: здесь только мтеод для получения всех оценок, а остльные методы иерархически
public class MarkController {

    private final MarkService markService;
    //private final PersonService personService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final SubjectService subjectService;

    MarkController(MarkService markService, TeacherService teacherService, StudentService studentService, SubjectService subjectService) {
        this.markService=markService;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @LogAnnotation
    @GetMapping("/")
    List<Mark> getAll() {
        return markService.getAll();
    }

    @LogAnnotation
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    Mark createPerson(@Valid @RequestBody MarkDto markDto) {

        Person teacher = teacherService.findById(markDto.getTeacherId());
        Person student = studentService.findById(markDto.getStudentId());
        Subject subject = subjectService.findById(markDto.getSubjectId());

        Mark newMark = new Mark();
        newMark.setTeacher(teacher);
        newMark.setStudent(student);
        newMark.setSubject(subject);
        newMark.setMark(markDto.getMark());

        return markService.add(newMark);
    }

    @LogAnnotation
    @GetMapping("/{id}")
    Mark getOnePerson(@PathVariable Long id) {

        return markService.findById(id);

    }


    //TODO: не так, как должно было быть
    //id будет внутри, так что не нужно, по идее
    @LogAnnotation
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Mark updateMarkValue(@Valid @RequestBody Mark mark, @PathVariable Long id) {

        Mark markForUpdate = markService.findById(id);
        markForUpdate.setMark(mark.getMark());

        return markService.edit(markForUpdate);
    }

    @LogAnnotation
    @DeleteMapping("/{id}")
    void deleteStudent(@PathVariable Long id) {
        markService.delete(id);
    }



}
