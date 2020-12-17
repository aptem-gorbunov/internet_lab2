package com.example._2lab2_8.controller;

import com.example._2lab2_8.aop.LogAnnotation;
import com.example._2lab2_8.authorization.annotation.IsAdmin;
import com.example._2lab2_8.entity.Person;
import com.example._2lab2_8.entity.Role;
import com.example._2lab2_8.entity.dto.MainPersonInfoDto;
import com.example._2lab2_8.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
    ResponseEntity<Person> createPerson(@Valid @RequestBody Person newStudent, BindingResult result) {

        try {
            if (result.hasErrors()){
                String errors = "";
                for(ObjectError er : result.getAllErrors()){
                    errors += er.getObjectName() + ": " + er.getDefaultMessage() + "\n";
                }

                return new ResponseEntity(errors,HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity(service.add(newStudent), HttpStatus.CREATED);

        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }

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

    @IsAdmin
    @LogAnnotation
    @DeleteMapping("/{id}")
    void deleteStudent(@PathVariable Long id) {
        service.delete(id);
    }
}
