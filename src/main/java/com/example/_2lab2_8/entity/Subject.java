package com.example._2lab2_8.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "subjects")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Subject implements Serializable {

    //TODO: https://www.baeldung.com/hibernate-inheritance
    //TODO: https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
    //TODO: https://www.baeldung.com/hibernate-one-to-many

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "subject name cannot be blank")
    @Size(min=1, max=50, message = "Subject name should be from 1 to 50 symbols")
    private String name;

    //@OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    //private List<TeacherSubject> teachers;

    public Subject() {
    }

    public Subject(Long id, @Size(min = 1, max = 50, message = "Subject name should be from 1 to 50 symbols") String name) {//, List<TeacherSubject> teachers) {
        this.id = id;
        this.name = name;
        //this.teachers = teachers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public List<TeacherSubject> getTeachers() {
    //    return teachers;
    //}

    //public void setTeachers(List<TeacherSubject> teachers) {
    //    this.teachers = teachers;
    //}
}
