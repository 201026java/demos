package com.revature.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ControllerAdvice;

import com.revature.controllers.UserController;

@ControllerAdvice(assignableTypes = UserController.class)
public class UserControllerAdvice {

//	@ExceptionHandler(UserNotFoundException.class)
//	@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "No such User")
	public void handleUserNotFound(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		System.out.println(req.getSession(false));
		System.out.println(res.getHeaderNames());
		System.out.println(session.getAttributeNames());
	}
}
