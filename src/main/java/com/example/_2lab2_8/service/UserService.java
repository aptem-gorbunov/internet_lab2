package com.example._2lab2_8.service;

import com.example._2lab2_8.entity.User;
import com.example._2lab2_8.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class UserService implements IUserService{

    private UserRepository repository;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public User add(User user) {
        return repository.saveAndFlush(user);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public User edit(User user) {
        return repository.saveAndFlush(user);
    }

    //TODO: рекурсия в JSON: https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion

    @Override
    public List<User> getAll() {
        List<User> res = repository.findAll();
        return res;
    }
}
