package com.example._2lab2_8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=3, max=30, message = "The surname should be 3-30 symbols")
    private String surname;
    @Size(min=3, max=30, message = "The firstName should be 3-30 symbols")
    private String firstName;
    @Size(min=3, max=30, message = "The patronymic should be 3-30 symbols")
    private String patronymic;


    private Integer age;
}
