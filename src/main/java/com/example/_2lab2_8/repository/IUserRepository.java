package com.example._2lab2_8.repository;

import com.example._2lab2_8.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
