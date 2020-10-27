package com.revature.aspects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class AuthorizationAspect {

	@Around("@annotation(com.revature.annotations.Authorized)")
	public Object authenticate(ProceedingJoinPoint pjp) throws Throwable {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getResponse();
		
		HttpSession session = request.getSession(false);
		
		if(session == null || session.getAttribute("currentUser") == null) {
			response.sendError(401, "You must be logged in to access this resource");
			return null;
		}
		
//		String auth = request.getHeader("Authorization");
//		// Proceed to use this to evaluate the JWT
//		
//		if(auth == null) {
//			response.sendError(401, "You must be authorized to access this resource");
//			return null;
//		}
		
		// They are logged in
		Object result = pjp.proceed(pjp.getArgs());
		return result;
	}
}
