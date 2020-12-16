package com.example._2lab2_8.controller;

import com.example._2lab2_8.aop.LogAnnotation;
import com.example._2lab2_8.entity.Person;
import com.example._2lab2_8.entity.Role;
import com.example._2lab2_8.entity.dto.MainPersonInfoDto;
import com.example._2lab2_8.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//TODO: рекурсия в JSON: https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion

//TODO: https://memorynotfound.com/spring-security-forgot-password-send-email-reset-password/

@RestController
@RequestMapping("people")
public class PersonController {

    private final PersonService service;

    PersonController(PersonService service) {
        this.service=service;
    }

    @LogAnnotation
    @GetMapping("/")
    List<Person> getAll() {
            return service.getAll();
    }

    @LogAnnotation
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    Person createPerson(@Valid @RequestBody Person newStudent) {
        return service.add(newStudent);
    }

    @LogAnnotation
    @GetMapping("/{id}")
    Person getOnePerson(@PathVariable Long id) {

        return service.findById(id);

    }


    @LogAnnotation
    @PutMapping("/{id}/main")
    Person updateMainPersonInfo(@PathVariable Long id,  @RequestBody MainPersonInfoDto newInfo) {

        Person personForUpdate = service.findById(id);

        personForUpdate.setFirstName(newInfo.getFirstName());
        personForUpdate.setSurname(newInfo.getSurname());
        personForUpdate.setPatronymic(newInfo.getPatronymic());
        personForUpdate.setAge(newInfo.getAge());

        return service.edit(personForUpdate);
    }

    @LogAnnotation
    @DeleteMapping("/{id}")
    void deleteStudent(@PathVariable Long id) {
        service.delete(id);
    }
}
