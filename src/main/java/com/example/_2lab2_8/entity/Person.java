package com.example._2lab2_8.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;


//TODO: https://memorynotfound.com/hibernate-jpa-joined-table-inheritance-example/

@Entity
@Table(name = "people")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
//@DiscriminatorValue(value="employee")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Size(min=3, max=30, message = "The surname should be 3-30 symbols")
    protected String surname;
    @Size(min=3, max=30, message = "The firstName should be 3-30 symbols")
    protected String firstName;
    @Size(min=3, max=30, message = "The patronymic should be 3-30 symbols")
    protected String patronymic;

    protected Integer age;


    @Size(min = 3, max=32, message = "Username length should be from 3 to 32 symbols")
    private String username;
    @Size(min = 8, max=50, message = "Password length should be from 3 to 32 symbols")
    private String password;

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
//TODO: можно добавить ещё один уникальный id (используется при регистрации пользователей как ключ, который нужно ввести для тго, чтобы начать прцосс создания пользовательского аккаунта)




    //TODO: https://stackoverflow.com/questions/29602386/how-does-the-fetchmode-work-in-spring-data-jpa
    // The fetch mode will only work when selecting the object by id i.e. using entityManager.find(). Since Spring Data will always create a query, the fetch mode configuration will have no use to you. You can either use dedicated queries with fetch joins or use entity graphs.

    //TODO: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.entity-graph

//можно и нормально ключи подписать
//    @JoinColumn(
//            name = "author_id",
//            nullable = false,
//            foreignKey = @ForeignKey(
//                    name = "fk_books_authors_id"
//            )
//    )

    //чтобы не дублировать ключ в users (там ещё и @MapsId)
//    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, optional = true, fetch = FetchType.LAZY)
//    @PrimaryKeyJoinColumn
//    //TODO: https://fasterxml.github.io/jackson-annotations/javadoc/2.5/com/fasterxml/jackson/annotation/JsonManagedReference.html
//    @JsonManagedReference
//    private User user;

    public Person() {
    }

    public Person(Long id, @Size(min = 3, max = 30, message = "The surname should be 3-30 symbols") String surname, @Size(min = 3, max = 30, message = "The firstName should be 3-30 symbols") String firstName, @Size(min = 3, max = 30, message = "The patronymic should be 3-30 symbols") String patronymic, Integer age, User user) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.age = age;
        //this.user = user;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(patronymic, person.patronymic) &&
                Objects.equals(age, person.age);// &&
                //Objects.equals(user, person.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, firstName, patronymic, age/*, user*/);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", age=" + age +
                '}';
    }
}
