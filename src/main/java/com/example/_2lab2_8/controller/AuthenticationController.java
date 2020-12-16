package com.example._2lab2_8.controller;

import com.example._2lab2_8.aop.LogAnnotation;
import com.example._2lab2_8.entity.Person;
import com.example._2lab2_8.entity.Role;
import com.example._2lab2_8.entity.dto.PersonDto;
import com.example._2lab2_8.entity.dto.UserDto;
import com.example._2lab2_8.exception.CannotFindPersonByUsername;
import com.example._2lab2_8.exception.EmailIsBusy;
import com.example._2lab2_8.exception.UsernameIsBusy;
import com.example._2lab2_8.service.PersonService;
import com.example._2lab2_8.utils.Crypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.SecureRandom;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class AuthenticationController {

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    private final PersonService service;

    AuthenticationController(PersonService service) {
        this.service=service;
    }

    @LogAnnotation
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    Person signUp(@Valid @RequestBody Person newPerson) {

        Boolean existsByUsername = service.existsByUsername(newPerson.getUsername());

        if (existsByUsername) {
            throw new UsernameIsBusy();
        }
        else {
            Boolean existsByEmail = service.existsByEmail(newPerson.getEmail());

            if (existsByEmail){
                throw new EmailIsBusy();
            }
            else {

                String hashedPassword = Crypto.hashPassword(newPerson.getPassword());

                newPerson.setPassword(hashedPassword);

                newPerson.setEmailConfirmed(false);
                newPerson.setRole(null);

                return service.add(newPerson);
            }
        }


    }

    @LogAnnotation
    @PostMapping("/login")
    void signIn(@Valid @RequestBody UserDto loggingUser) {

        String searchingUsername = loggingUser.getUsername();

        Optional<Person> personByUsername = service.findByUsername(searchingUsername);

        if (personByUsername.isPresent()){

            String plainPasswordFromRequest = loggingUser.getPassword();
            String foundedPersonPassword = personByUsername.get().getPassword();


            Boolean passwordsMatch = Crypto.mathesPasswords(foundedPersonPassword, plainPasswordFromRequest);

            if (passwordsMatch) {

                System.out.println("Passwords match");

            }
            else {
                System.out.println("Passwords do not match");
            }



        }
        else {
            throw new CannotFindPersonByUsername();
        }


    }

}
