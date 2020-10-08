package com.example._2lab2_8.service;

import com.example._2lab2_8.entity.Teacher;
import com.example._2lab2_8.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> findById(long id);
    User add(User user);
    void delete(long id);
    //Student getByName(String name);
    User edit(User user);
    List<User> getAll();
}
