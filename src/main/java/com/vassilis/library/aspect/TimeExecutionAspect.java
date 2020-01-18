package com.vassilis.library.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimeExecutionAspect {

    // Advice
    @Around("@annotation(TimeExecution)")
    public Object timeExecution(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        System.out.println(String.format("Execution time %d (ms)", System.currentTimeMillis() - start));
        return result;
    }
}
