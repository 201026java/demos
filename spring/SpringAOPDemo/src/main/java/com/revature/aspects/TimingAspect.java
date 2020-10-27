package com.revature.aspects;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimingAspect {
	
	private static Map<String, Long> totalDurations = new HashMap<>();
	
	private static Logger log = LogManager.getLogger(TimingAspect.class);
	/*
	 * My goal is to write some advice to time how long every method takes to execute
	 */
	
	@Around("@annotation(com.revature.annotations.Timed)")
	public Object timeAllMethods(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.nanoTime();
		
		Object result = pjp.proceed();
		
		long duration = System.nanoTime() - startTime;
		
		log.info(pjp.getTarget() + " invoked " + pjp.getSignature() + " and took " + duration / 1000 + " us");
		
		if(totalDurations.get(pjp.getSignature().toString()) != null) {
			totalDurations.put(pjp.getSignature().toString(), duration + totalDurations.get(pjp.getSignature().toString()));
		} else {
			totalDurations.put(pjp.getSignature().toString(), duration);
		}
		
		return result;
	}

	public static Map<String, Long> getTotalDurations() {
		return totalDurations;
	}
}
