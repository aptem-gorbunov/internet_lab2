package com.example._2lab2_8.repository;

import com.example._2lab2_8.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> getPersonByUsername(@Size(min = 3, max = 32, message = "Username length should be from 3 to 32 symbols") String username);
    Optional<Person> getPersonByEmail(@Email(message = "Provided email field is invalid") @Size(min = 8, max = 320, message = "Email length should be from 8 to 320 symbols") String email);

    Boolean existsByEmail(@Email(message = "Provided email field is invalid") @Size(min = 8, max = 320, message = "Email length should be from 8 to 320 symbols") String email);

    Boolean existsByUsername(@Size(min = 3, max = 32, message = "Username length should be from 3 to 32 symbols") String username);
}
