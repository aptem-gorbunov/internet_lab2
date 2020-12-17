//package com.example._2lab2_8.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthenticationService {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    PersonService personService;
//
//    @Autowired
//    public void setPersonService(PersonService personService) {
//        this.personService = personService;
//    }
//
//    public void autoLogin(String username, String password){
//        UserDetails userDetails = personService.loadUserByUsername(username);
//
//        authenticationManager.authenticate(userDetails);
//    }
//}
