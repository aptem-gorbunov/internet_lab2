package com.example._2lab2_8.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

public class StudentForm {

    @Size(min=3, max=30, message = "The surname should be 3-30 symbols")
    private String surname;
    @Size(min=3, max=30, message = "The firstName should be 3-30 symbols")
    private String firstName;
    @Size(min=3, max=30, message = "The patronymic should be 3-30 symbols")
    private String patronymic;


    public StudentForm() {
    }
}
