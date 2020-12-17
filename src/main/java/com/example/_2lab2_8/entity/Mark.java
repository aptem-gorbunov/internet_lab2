package com.example._2lab2_8.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "marks")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Mark implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(optional = false)
//    private TeacherSubject teacherSubject;

    @ManyToOne(optional = false)
    private Person student;

    @ManyToOne(optional = false)
    private Person teacher;

    @ManyToOne(optional = false)
    private Subject subject;

    @Range(min = 2, max = 10, message = "Mark value should be between 2 and 10")
    private int mark;

    public Mark() {
    }

    public Mark(Long id, Person student, Person teacher, Subject subject, int mark) {
        this.id = id;
        this.student = student;
        this.teacher = teacher;
        this.subject = subject;
        this.mark = mark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getStudent() {
        return student;
    }

    public void setStudent(Person student) {
        this.student = student;
    }

    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
