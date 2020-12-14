package com.example._2lab2_8.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "teachers")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@DiscriminatorValue(value="teacher")
public class Teacher extends Person implements Serializable {

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<TeacherSubject> subjects;

    public Teacher() {
    }

    public Teacher(List<TeacherSubject> subjects) {
        this.subjects = subjects;
    }

    public Teacher(Long id, @Size(min = 3, max = 30, message = "The surname should be 3-30 symbols") String surname, @Size(min = 3, max = 30, message = "The firstName should be 3-30 symbols") String firstName, @Size(min = 3, max = 30, message = "The patronymic should be 3-30 symbols") String patronymic, Integer age, User user, List<TeacherSubject> subjects) {
        super(id, surname, firstName, patronymic, age, user);
        this.subjects = subjects;
    }

    public List<TeacherSubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<TeacherSubject> subjects) {
        this.subjects = subjects;
    }
}
