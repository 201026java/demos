package com.revature;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.revature.config.AppConfig;
import com.revature.services.IUserService;

public class Driver {

	public static void main(String[] args) {
//		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//
//		User u = (User) ac.getBean("user");
//		
//		User u2 = ac.getBean(User.class);
//		
//		User u3 = ac.getBean("user", User.class);
//		
//		System.out.println(u);
//		
//		System.out.println(u == u2);
//		System.out.println(u == u3);
//		
//		System.out.println(u.getAccount() == u2.getAccount());
//		
//		IUserService us = ac.getBean(IUserService.class);
//		
//		System.out.println(us.findAll());
		
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
		IUserService us = ac.getBean(IUserService.class);
		
		System.out.println(us.findAll());
		
		((AnnotationConfigApplicationContext) ac).close();
	}
}
