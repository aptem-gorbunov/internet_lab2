package com.example._2lab2_8.config;

import com.example._2lab2_8.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(
//        prePostEnabled = true,
//        securedEnabled = true)//,
//jsr250Enabled = true)
public class SecurityConfiguration1 extends WebSecurityConfigurerAdapter {

    @Autowired
    private PersonService personService;

    //добавил bean в application
    //@Autowired
    //private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        //auth.userDetailsService(personService).passwordEncoder(passwordEncoder);
        auth.userDetailsService(personService);
    }


}
