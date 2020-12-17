package com.example._2lab2_8.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class LogAspect {

    Logger logger = LogManager.getLogger(LogAspect.class);

    //@Pointcut("execution(public * com.example._2lab2_8.controller.MainController.*(..))")
    @Pointcut("@annotation(LogAnnotation)")
    public void callAtMainController() {

    }

    @Before("callAtMainController()")
    public void beforeCallMethod(JoinPoint jp){
        String args = Arrays.stream(jp.getArgs())
                .map(a->a.toString())
                .collect(Collectors.joining(","));

        logger.info("before "+ jp.toString()   + ", args=[" + args + "]");
    }

    @After("callAtMainController()")
    public void afterCallAt(JoinPoint jp) {
        logger.info("after " + jp.toString());
        //log.info("after " + jp.toString());
    }

    @AfterThrowing(pointcut = "@annotation(LogAnnotation)",throwing = "ex")
    public void afterThrowController(Exception ex){
        logger.error("---Controller error: " + ex.getMessage() + "\n stack: " + Arrays.toString(ex.getStackTrace()));
    }

}
