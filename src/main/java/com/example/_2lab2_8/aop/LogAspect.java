package com.example._2lab2_8.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class LogAspect {
    //@Pointcut("execution(public * com.example._2lab2_8.controller.MainController.*(..))")
    @Pointcut("@annotation(LogAnnotation)")
    public void callAtMainController() {

    }

    @Before("callAtMainController()")
    public void beforeCallMethod(JoinPoint jp){
        String args = Arrays.stream(jp.getArgs())
                .map(a->a.toString())
                .collect(Collectors.joining(","));

        System.out.println("before "+ jp.toString()   + ", args=[" + args + "]");


    }

    @After("callAtMainController()")
    public void afterCallAt(JoinPoint jp) {
        System.out.println("after " + jp.toString());
        //log.info("after " + jp.toString());
    }

}
