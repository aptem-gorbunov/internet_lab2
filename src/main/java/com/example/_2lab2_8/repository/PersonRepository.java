package com.example._2lab2_8.repository;

import com.example._2lab2_8.entity.Mark;
import com.example._2lab2_8.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
