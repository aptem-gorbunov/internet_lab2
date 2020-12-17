package com.example._2lab2_8;

import com.example._2lab2_8.entity.Person;
import com.example._2lab2_8.entity.dto.UserDto;
import com.example._2lab2_8.exception.PersonNotFound;
import com.example._2lab2_8.service.PersonService;
import io.jsonwebtoken.lang.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Objects;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



//https://www.baeldung.com/integration-testing-a-rest-api
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UnitTests {

    @Autowired
    private PersonService personService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void response_with_4xx_status_without_admin_role() throws Exception {
        mockMvc.perform(get("/people/1")).andExpect(status().is4xxClientError());
    }

    @Test
    public void test_user_exists_by_username() {

        System.out.println(personService);

        Assert.isTrue(personService.existsByUsername("username1"));
    }

    @Test
    public void test_get_person_with_same_as_in_parameter() throws Exception {

        String username = "username1";
        Optional<Person> personByUsername = personService.findByUsername(username);

        if (personByUsername.isEmpty()) {
            throw new Exception("Cannot find user by username");
        }

        Assert.isTrue(Objects.equals(username, personByUsername.get().getUsername()));

    }

    @Test
    public void assert_true_user_exists_by_email() throws Exception {

        Assert.isTrue(personService.existsByEmail("email@email.com"));

    }

}
