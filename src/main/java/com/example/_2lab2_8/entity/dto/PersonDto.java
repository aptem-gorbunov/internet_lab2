package com.example._2lab2_8.entity.dto;

import com.example._2lab2_8.entity.Mark;
import com.example._2lab2_8.entity.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class PersonDto {

    protected Long id;

    @Size(min=3, max=30, message = "The surname should be 3-30 symbols")
    protected String surname;
    @Size(min=3, max=30, message = "The firstName should be 3-30 symbols")
    protected String firstName;
    @Size(min=3, max=30, message = "The patronymic should be 3-30 symbols")
    protected String patronymic;

    private Boolean isEmailConfirmed = false;



    protected Integer age;


    @Size(min = 3, max=32, message = "Username length should be from 3 to 32 symbols")
    private String username;
    @Size(min = 8, max=50, message = "Password length should be from 3 to 32 symbols")
    private String password;

    @Email(message = "Provided email field is invalid")
    @Size(min = 8, max=320, message = "Email length should be from 8 to 320 symbols")
    private String email;

    private List<Mark> receivedMarks;
    private List<Mark> givenMarks;

    private Role role = null;

    public PersonDto() {
    }


    public PersonDto(Long id, @Size(min = 3, max = 30, message = "The surname should be 3-30 symbols") String surname, @Size(min = 3, max = 30, message = "The firstName should be 3-30 symbols") String firstName, @Size(min = 3, max = 30, message = "The patronymic should be 3-30 symbols") String patronymic, Boolean isEmailConfirmed, Integer age, @Size(min = 3, max = 32, message = "Username length should be from 3 to 32 symbols") String username, @Size(min = 8, max = 50, message = "Password length should be from 3 to 32 symbols") String password, @Email(message = "Provided email field is invalid") @Size(min = 8, max = 320, message = "Email length should be from 8 to 320 symbols") String email, List<Mark> receivedMarks, List<Mark> givenMarks, Role role) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.isEmailConfirmed = isEmailConfirmed;
        this.age = age;
        this.username = username;
        this.password = password;
        this.email = email;
        this.receivedMarks = receivedMarks;
        this.givenMarks = givenMarks;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getEmailConfirmed() {
        return isEmailConfirmed;
    }

    public void setEmailConfirmed(Boolean emailConfirmed) {
        isEmailConfirmed = emailConfirmed;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Mark> getReceivedMarks() {
        return receivedMarks;
    }

    public void setReceivedMarks(List<Mark> receivedMarks) {
        this.receivedMarks = receivedMarks;
    }

    public List<Mark> getGivenMarks() {
        return givenMarks;
    }

    public void setGivenMarks(List<Mark> givenMarks) {
        this.givenMarks = givenMarks;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDto personDto = (PersonDto) o;
        return Objects.equals(id, personDto.id) &&
                Objects.equals(surname, personDto.surname) &&
                Objects.equals(firstName, personDto.firstName) &&
                Objects.equals(patronymic, personDto.patronymic) &&
                Objects.equals(isEmailConfirmed, personDto.isEmailConfirmed) &&
                Objects.equals(age, personDto.age) &&
                Objects.equals(username, personDto.username) &&
                Objects.equals(password, personDto.password) &&
                Objects.equals(email, personDto.email) &&
                Objects.equals(receivedMarks, personDto.receivedMarks) &&
                Objects.equals(givenMarks, personDto.givenMarks) &&
                role == personDto.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, firstName, patronymic, isEmailConfirmed, age, username, password, email, receivedMarks, givenMarks, role);
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", isEmailConfirmed=" + isEmailConfirmed +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", receivedMarks=" + receivedMarks +
                ", givenMarks=" + givenMarks +
                ", role=" + role +
                '}';
    }
}
