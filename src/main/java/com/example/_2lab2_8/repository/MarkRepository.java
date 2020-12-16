package com.example._2lab2_8.repository;

import com.example._2lab2_8.entity.Mark;
import com.example._2lab2_8.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {

    List<Mark> getMarksByTeacher(Person teacher);

    List<Mark> getMarksByStudent(Person student);
}
