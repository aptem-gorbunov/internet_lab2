package com.example._2lab2_8;

import com.example._2lab2_8.entity.Mark;
import com.example._2lab2_8.entity.Person;
import com.example._2lab2_8.entity.Role;
import com.example._2lab2_8.entity.Subject;
import com.example._2lab2_8.service.MarkService;
import com.example._2lab2_8.service.PersonService;
import com.example._2lab2_8.service.SubjectService;
import com.example._2lab2_8.utils.Crypto;
import io.jsonwebtoken.lang.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntergaritonTests {

    @Autowired
    private PersonService personService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private MarkService markService;

    @Test
    public void test_mark_creation_with_existing_data() {

        Person student = personService.findById(4);
        Person teacher = personService.findById(1);

        Subject subject = subjectService.findById(1);

        Mark newMark = new Mark();
        newMark.setTeacher(teacher);
        newMark.setStudent(student);
        newMark.setSubject(subject);
        newMark.setMark(10);

        Mark insertedMark = markService.add(newMark);

        Assert.notNull(insertedMark);

    }

    @Test
    public void test_marks_count_after_person_creation() {



        Person student = new Person();
        student.setPassword(Crypto.hashPassword("password"));
        student.setUsername("username234FE__");
        student.setEmail("student_emailGBVVRH@email.com");
        student.setFirstName("student first name");
        student.setSurname("student surname");
        student.setPatronymic("student patronymic");
        student.setAge(20);
        student.setRole(Role.ROLE_STUDENT);


        Person insertedStudent = personService.add(student);

        List<Mark> marks = markService.getMarksByStudent(insertedStudent);

        Assert.isTrue(marks.size()==0);

        personService.delete(insertedStudent.getId());

    }

}
