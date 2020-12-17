package com.example._2lab2_8.controller;

import com.example._2lab2_8.aop.LogAnnotation;
import com.example._2lab2_8.entity.Person;
import com.example._2lab2_8.entity.dto.JwtResponse;
import com.example._2lab2_8.entity.dto.UserDto;
import com.example._2lab2_8.exception.EmailIsBusy;
import com.example._2lab2_8.exception.PersonNotFound;
import com.example._2lab2_8.exception.PersonNotVerified;
import com.example._2lab2_8.exception.UsernameIsBusy;
import com.example._2lab2_8.service.EmailService;
import com.example._2lab2_8.service.PersonService;
import com.example._2lab2_8.utils.Crypto;
import com.example._2lab2_8.utils.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/")
public class AuthenticationController {

//    @Autowired
//    private PasswordEncoder passwordEncoder;



    private final PersonService service;

    AuthenticationController(PersonService service) {
        this.service=service;
    }

//    @IsAdmin
//    @GetMapping("/admin")
//    public String geg() {
//        return "Admin string";
//    }

    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @LogAnnotation
    @GetMapping("/verify/{token}")
    ResponseEntity<Person> verifyEmail(@PathVariable String token) {

        Person personFromToken = service.findByUsername(jwtUtility.getUsernameFromToken(token)).orElseThrow(PersonNotFound::new);

        personFromToken.setEmailConfirmed(true);

        Person updatedPerson = service.edit(personFromToken);

        return ResponseEntity.ok(updatedPerson);
    }


    @LogAnnotation
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Person> signUp(@Valid @RequestBody Person newPerson, BindingResult result) {

        try {
            if (result.hasErrors()){
                String errors = "";
                for(ObjectError er : result.getAllErrors()){

                    errors += er.getObjectName() + ": " + er.getDefaultMessage() + "\n";
                }

                return new ResponseEntity(errors,HttpStatus.BAD_REQUEST);
            }

            Boolean existsByUsername = service.existsByUsername(newPerson.getUsername());

            if (existsByUsername) {
                throw new UsernameIsBusy();
            }
            else {

                Boolean existsByEmail = service.existsByEmail(newPerson.getEmail());

                if (existsByEmail){
                    throw new EmailIsBusy();
                }
                else {

                    String hashedPassword = Crypto.hashPassword(newPerson.getPassword());

                    newPerson.setPassword(hashedPassword);

                    newPerson.setEmailConfirmed(false);
                    newPerson.setRole(null);

                    Person insertedPerson = service.add(newPerson);

                    UserDetails userDetails = service.loadUserByUsername(insertedPerson.getUsername());
                    String verificationToken = jwtUtility.generateToken(userDetails);

                    emailService.sendHTMLMessage(newPerson.getEmail(), "Email verification", verificationToken);



                    return new ResponseEntity(insertedPerson, HttpStatus.CREATED);
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }




    }

//    @LogAnnotation
//    @PostMapping("/login")
//    void signIn(@Valid @RequestBody UserDto loggingUser) {
//
//        //String username = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getDetails()).getUsername();
//
//        String searchingUsername = loggingUser.getUsername();
//
//        Optional<Person> personByUsername = service.findByUsername(searchingUsername);
//
//        if (personByUsername.isPresent()){
//
//            String plainPasswordFromRequest = loggingUser.getPassword();
//            String foundedPersonPassword = personByUsername.get().getPassword();
//
//
//            Boolean passwordsMatch = Crypto.mathesPasswords(foundedPersonPassword, plainPasswordFromRequest);
//
//            if (passwordsMatch) {
//
//                System.out.println("Passwords match");
//
//            }
//            else {
//                System.out.println("Passwords do not match");
//            }
//
//
//
//        }
//        else {
//            throw new CannotFindPersonByUsername();
//        }
//
//
//    }


    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

//
//    @GetMapping("/")
//    public String home() {
//        return "Welcome to Daily Code Buffer!!";
//    }

    @LogAnnotation
    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody UserDto userCredentials) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userCredentials.getUsername(),
                            userCredentials.getPassword()
                    )
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        Person person = service.findByUsername(userCredentials.getUsername()).orElseThrow(PersonNotFound::new);

        if (!person.getEmailConfirmed()) {
            throw new PersonNotVerified();
        }

        final UserDetails userDetails
                = service.loadUserByUsername(userCredentials.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);

        return new JwtResponse(token);
    }

}
