package com.example._2lab2_8.config;


import com.example._2lab2_8.authorization.annotation.filter.JwtFilter;
import com.example._2lab2_8.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//https://www.youtube.com/watch?v=lA18U8dGKF8

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    private PersonService userService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/authenticate")
//                    .permitAll()
//                    .anyRequest()
//                    .authenticated()
//                .and()
//                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//                http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeRequests()
//
//                        .antMatchers(HttpMethod.POST, "/people/**", "/teachers/**").hasAnyRole("ADMIN")
//
//                        .antMatchers(HttpMethod.GET, "/students/**", "/subjects/**", "/marks/**").hasAnyRole("ADMIN", "TEACHER", "STUDENT")
//                        //.antMatchers(, "/students/**").hasAnyRole("ADMIN")
//                        .antMatchers(HttpMethod.PUT, "/students/**").hasAnyRole("ADMIN", "STUDENT")
//
//                        .antMatchers(HttpMethod.GET, "/marks/**").hasAnyRole( "TEACHER", "STUDENT", "ADMIN")
//                        .antMatchers(HttpMethod.POST, "/marks/**").hasAnyRole( "TEACHER")
//                        .antMatchers(HttpMethod.PUT, "/marks/**").hasAnyRole( "TEACHER")
//
//                 //всё остальное разрешаю
//                .antMatchers("/authenticate", "/register", "/**")
//                    .permitAll()
//                    .anyRequest()
//                    .authenticated()
//                .and()
//                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//

        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/people/*").hasRole("ADMIN")
                .antMatchers("/teachers/").hasRole("ADMIN")
                .antMatchers("/students/").hasRole("ADMIN")
                .antMatchers("/marks/*").hasAnyRole("ADMIN", "TEACHER", "STUDENT")
                .antMatchers("/register", "/authenticate","/getUser/","/activate/").permitAll()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        //http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }
}