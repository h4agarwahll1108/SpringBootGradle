package com.harshit1930815.ticketSystem.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//7. Create a Logging Aspect to log before and after any method execution - 5 points
@Aspect
@Component
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.harshit1930815.ticketSystem..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Executing Before: " + joinPoint.getSignature().toShortString());
    }

    @After("execution(* com.harshit1930815.ticketSystem..*(..))")
    public void logAfter(JoinPoint joinPoint) {

        logger.info("Executed After: " + joinPoint.getSignature().toShortString());
    }
}


