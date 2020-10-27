package com.revature.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.revature.models.Car;

@Aspect
@Component
public class LoggingAspect {

	private static Logger log = LogManager.getLogger(LoggingAspect.class);
	
	@Before("within(com.revature.models.*)")
	public void logAllModelMethods(JoinPoint jp) {
		log.info(jp.getTarget() + " invoked " + jp.getSignature());
	}
	
	@AfterThrowing(pointcut = "execution(void validate())", throwing = "ex")
	public void logCarValidate(JoinPoint jp, Exception ex) {
		log.info(jp.getTarget() + " invoked " + jp.getSignature() + " throwing " + ex.getClass(), ex);
	}
	
	@Around("execution(void validate())")
	public void aroundExample(ProceedingJoinPoint pjp) throws Throwable {
		Car c = (Car) pjp.getTarget();
		log.info(c + " is about to be validated...");
		
		int year = c.getYear();
		
		if(year < 2000) {
			log.warn("Car Year is too old, it is risky to validate");
		} else {
			pjp.proceed();
		}
	}
	
	@AfterReturning(pointcut = "within(com.revature.models.*) && execution(* get*())", returning = "returnedObject")
	public void logGetter(JoinPoint jp, Object returnedObject) {
		log.info(jp.getTarget() + " invoked " + jp.getSignature() + ", and returned " + returnedObject);
	}
}
