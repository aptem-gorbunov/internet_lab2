package com.example._2lab2_8.entity.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class MainPersonInfoDto {


    @NotNull(message = "Age is required")
    @Range(min = 16, max = 100, message = "Age should be between 16 and 100")
    protected Integer age;


    @NotBlank(message = "surname cannot be blank")
    @Size(min=3, max=30, message = "The surname should be 3-30 symbols")
    protected String surname;

    @NotBlank(message = "surname cannot be blank")
    @Size(min=3, max=30, message = "The firstName should be 3-30 symbols")
    protected String firstName;

    @NotBlank(message = "surname cannot be blank")
    @Size(min=3, max=30, message = "The patronymic should be 3-30 symbols")
    protected String patronymic;

    public MainPersonInfoDto() {
    }

    public MainPersonInfoDto(@NotNull(message = "Age is required") @Range(min = 16, max = 100, message = "Age should be between 16 and 100") Integer age, @NotBlank(message = "surname cannot be blank") @Size(min = 3, max = 30, message = "The surname should be 3-30 symbols") String surname, @NotBlank(message = "surname cannot be blank") @Size(min = 3, max = 30, message = "The firstName should be 3-30 symbols") String firstName, @NotBlank(message = "surname cannot be blank") @Size(min = 3, max = 30, message = "The patronymic should be 3-30 symbols") String patronymic) {
        this.age = age;
        this.surname = surname;
        this.firstName = firstName;
        this.patronymic = patronymic;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainPersonInfoDto that = (MainPersonInfoDto) o;
        return Objects.equals(age, that.age) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(patronymic, that.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, surname, firstName, patronymic);
    }


}
