//package com.example._2lab2_8.authorization.annotation;
//
//import com.example._2lab2_8.service.PersonService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//
//@Component
//class AuthorizationProviderMy2123 implements AuthenticationProvider {
//
//    private PersonService personService;
//
//    @Autowired
//    public void setPersonService(PersonService personService) {
//        this.personService = personService;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication)
//            throws AuthenticationException {
//
//        String name = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        try{
//            UserDetails user = personService.loadUserByUsername(name);
//        }
//        catch (Exception e) {
//            throw new AuthenticationException("could not login");
//        }
//
//        return new UserNamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities()); // (4)
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
