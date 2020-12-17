package com.example._2lab2_8.config;

import com.example._2lab2_8.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

//@EnableWebSecurity
//
//@Configuration
//@EnableConfigurationProperties
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private PersonService personService;
//
//
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        //auth.userDetailsService(())
////        super.configure(auth);
////    }
//
//    //переопрделение стандартных настроек
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests().anyRequest().authenticated()
//                //.and().httpBasic()
//                .and().sessionManagement().disable();
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder builder)
//            throws Exception {
//        builder.userDetailsService(personService);
//    }
//
//
//
////    @Bean
////    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
////        http.authorizeExchange().anyExchange().permitAll();
////        return http.build();
////    }
//}


//https://www.dineshonjava.com/spring-security-java-based-configuration-with-example/

//https://www.marcobehler.com/guides/spring-security

//https://www.youtube.com/watch?v=lA18U8dGKF8

//@Configuration
//@EnableGlobalMethodSecurity(
//        prePostEnabled = true,
//        securedEnabled = true)//,
//        //jsr250Enabled = true)
class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {


    private PersonService personService;

    //добавил bean в application
    //@Autowired
    //private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        //auth.userDetailsService(personService).passwordEncoder(passwordEncoder);
        //auth.userDetailsService(personService);

    }

}