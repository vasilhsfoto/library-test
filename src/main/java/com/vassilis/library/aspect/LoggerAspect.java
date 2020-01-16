package com.vassilis.library.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggerAspect {

    // Advice
    @Before("@annotation(Logger)")
    public void logMethod(JoinPoint joinPoint) {
        log.info("Start of execution of method {} and params {}",
                joinPoint.getTarget().getClass().getName(),
                joinPoint.getArgs());
    }
}
