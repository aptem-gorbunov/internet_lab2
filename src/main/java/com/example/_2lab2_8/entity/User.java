package com.example._2lab2_8.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

//@Entity
//TODO: не создавало автоматически таблицу с именем users
//@Table(name="accounts")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
//TODO: без serializable вообще ничего в контроллере не возвращало (с ним появился индекс элемента полученного)
//TODO: надо было добавить Getter'ы и Setter'ы: https://stackoverflow.com/questions/49117622/spring-rest-controller-returns-empty-json-iterable-data-structure-why
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max=32, message = "Username length should be from 3 to 32 symbols")
    private String username;
    @Size(min = 8, max=50, message = "Password length should be from 3 to 32 symbols")
    private String password;

    private Role role;

    //TODO: https://vladmihalcea.com/the-best-way-to-map-a-onetoone-relationship-with-jpa-and-hibernate/

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "person_id")
    //TODO: @JsonBackReference - https://fasterxml.github.io/jackson-annotations/javadoc/2.5/com/fasterxml/jackson/annotation/JsonBackReference.html#:~:text=Annotation%20Type%20JsonBackReference&text=Annotation%20used%20to%20indicate%20that,%2C%20Map%2C%20Array%20or%20enumeration.
    @JsonBackReference
    private Person person;

    public User() {
    }

    public User(Long id, @Size(min = 3, max = 32, message = "Username length should be from 3 to 32 symbols") String username, @Size(min = 8, max = 50, message = "Password length should be from 3 to 32 symbols") String password, Role role, Person person) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                role == user.role &&
                Objects.equals(person, user.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, role, person);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
