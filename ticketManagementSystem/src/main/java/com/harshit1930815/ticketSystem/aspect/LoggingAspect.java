package com.harshit1930815.ticketSystem.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.harshit1930815.ticketSystem.model.Ticket;

//7. Create a Logging Aspect to log before and after any method execution - 5 points
@Aspect
@Component
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.harshit1930815.ticketSystem..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Executing @Before: " + joinPoint.getSignature().toShortString());
    }

    @After("execution(* com.harshit1930815.ticketSystem..*(..))")
    public void logAfter(JoinPoint joinPoint) {

        logger.info("Executed @After: " + joinPoint.getSignature().toShortString());
    }
    
//	@Before("execution(* com.harshit1930815.ticketSystem.controller.*.*(..))")
//	public void logBeforeController(JoinPoint joinPoint) {
//		logger.info("Executing @BEFORE_CONTROLLER: " + joinPoint.getSignature().toShortString()
//				+ System.currentTimeMillis());
//	}
//
//	@Before("execution(* com.harshit1930815.ticketSystem.service.*.*(..))")
//	public void logBeforeService(JoinPoint joinPoint) {
//		logger.info(
//				"Executing @BEFORE_SERVICE: " + joinPoint.getSignature().toShortString() + System.currentTimeMillis());
//	}
//
//	@AfterReturning(value = "execution(* com.harshit1930815.ticketSystem.service.impl.TicketServiceImpl.createTicket(..))", returning = "ticket")
//	public void logAfterReturningController(JoinPoint joinPoint, Ticket ticket) {
//		logger.info("Executing @AFTER_RETURNNG_SERVICE: " + joinPoint.getSignature().toShortString()
//				+ System.currentTimeMillis());
//	}
//
//	@After("execution(* com.harshit1930815.ticketSystem.service.*.*(..))")
//	public void logAfterService(JoinPoint joinPoint) {
//		logger.info(
//				"Executing @AFTER_SERVICE: " + joinPoint.getSignature().toShortString() + System.currentTimeMillis());
//	}
//
//	@After("execution(* com.harshit1930815.ticketSystem.controller.*.*(..))")
//	public void logAfterController(JoinPoint joinPoint) {
//		logger.info("Executing @AFTER_CONTROLLER: " + joinPoint.getSignature().toShortString()
//				+ System.currentTimeMillis());
//	}
//
//	@AfterThrowing(value = "execution(* com.harshit1930815.ticketSystem.service.impl.TicketServiceImpl.createTicket(..))", throwing = "e")
//	public void logAfterThrowing(JoinPoint joinPoint, Exception e) {
//		logger.info("Executing @AFTER_THROWING_SERVICE: " + joinPoint.getSignature().toShortString()
//				+ System.currentTimeMillis());
//	}
//
//	@Around("execution(* com.harshit1930815.ticketSystem.service.impl.TicketServiceImpl.createTicket(..))")
//	public void logAroundServce(ProceedingJoinPoint joinPoint) throws Throwable {
//		logger.info(
//				"Executing @AROUND_SERVICE: " + joinPoint.getSignature().toShortString() + System.currentTimeMillis());
//		try {
//			joinPoint.proceed();
//		} catch (Exception e) {
//			logger.info("Executing @AROUND_SERVICE: " + joinPoint.getSignature().toShortString()
//					+ System.currentTimeMillis());
//		}
//		logger.info(
//				"Executing @AROUND_SERVICE: " + joinPoint.getSignature().toShortString() + System.currentTimeMillis());
//	}
}