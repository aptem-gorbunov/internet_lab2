package com.example._2lab2_8.controller;

import com.example._2lab2_8.entity.Student;
import com.example._2lab2_8.entity.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
//@Controller
public class MainController {

    private final List<Student> students = new ArrayList<>();
    private final List<Teacher> teachers = new ArrayList<>();




//    @GetMapping("/students")
//    public ModelAndView getStudentsPage(Model model) {
//
//         log.info("get students page");
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("students_list");
//        model.addAttribute("students", students);
//        return modelAndView;
//    }

    @GetMapping("/teachers")
    public ModelAndView getTeachersPage(Model model) {

        log.info("get teachers page");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teachers_list");
        model.addAttribute("teachers", teachers);
        return modelAndView;
    }

    @GetMapping("/create/teacher")
    public  ModelAndView displayTeacherCreationPage(Model model) {

        log.info("get teacher creation page");

        ModelAndView modelAndView = new ModelAndView("create_teacher");
        Teacher newTeacher = new Teacher();
        model.addAttribute("teacher", newTeacher);
        return modelAndView;
    }

    @PostMapping(value="/create/teacher")
    public String checkTeacherInfo(@Valid Teacher teacher, BindingResult bindingResult) {

        log.info("create new teacher");

        if (bindingResult.hasErrors()) {

            log.info("validation errors. Cannot create new teacher");

            return "create_teacher";
        }

        log.info("no validation errors. Can create new teacher");

        teachers.add(teacher);

        return "redirect:/teachers";
    }

//    TODO: если бы вместо модели я перердавал в метод класс, который должен быть внутри формы, то имя параметра я мог бы записать любое, но в форме я должен был бы использовать образение по имени класса (ещё и в виде abcDef)
//    TODO: если же возвращать modelAndView, то нужно добавить атрибут и в форме обращаться по его имени

//    @GetMapping("/create/student")
//    public  ModelAndView displayStudentCreationPage(Model model) {
//
//        log.info("get student creation page");
//
//        ModelAndView modelAndView = new ModelAndView("create_student");
//        Student newStudent = new Student();
//        model.addAttribute("student", newStudent);
//        return modelAndView;
//    }

//    @PostMapping(value="/create/student")
//    public String checkStudentInfo(@Valid Student student, BindingResult bindingResult) {
//
//        log.info("create new student");
//
//        if (bindingResult.hasErrors()) {
//
//            log.info("validation errors. Cannot create new student");
//
//            return "create_student";
//        }
//
//        log.info("no validation errors. Can create new teacher");
//
//        students.add(student);
//
//        return "redirect:/students";
//    }

}
