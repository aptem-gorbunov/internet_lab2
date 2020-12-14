package com.example._2lab2_8.controller;

import com.example._2lab2_8.entity.User;
import com.example._2lab2_8.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

//@RestController
//@RequestMapping("users")
public class UserController {
    private final UserService service;

    UserController(UserService service) {
        this.service=service;
    }

    @GetMapping("/all")
    List<User> getAllUsers() {
        return service.getAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    User createUser(@Valid @RequestBody User newUser) {
        return service.add(newUser);
    }

    @GetMapping("/{id}")
    Optional<User> getOneUser(@PathVariable Long id) {
        return service.findById(id);
    }

    //TODO: не так, как должно было быть
    @PutMapping("/edit/{id}")
    User updateUser(@Valid @RequestBody User updatedUser, @PathVariable Long id) {
        return service.edit(updatedUser);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        service.delete(id);
    }
}
