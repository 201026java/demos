package com.revature;

import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.aspects.TimingAspect;
import com.revature.exceptions.InvalidCarException;
import com.revature.models.Car;

public class Driver {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

		Car car = (Car) ac.getBean("car");
		
		System.out.println(car);
		
//		car.setId(1);
		car.setMake("Honda");
		car.setModel("Civic");
		car.setEngine("I4");
		car.setYear(2008);
		
		System.out.println(car);
		
		car.validate();
		
		car.setModel("Mustang");
		car.setMake("Ford");
		car.setYear(4);
		car.setEngine("v10");
		
//		try {
			car.validate();
//		} catch(InvalidCarException e) {
//		}
			
		car.setYear(2020);
		car.validate();
		
		car.getYear();
		car.getEngine();
		
		Random r = new Random();
		for(int i = 0; i < 10000; i++) {
//			car.setId(r.nextInt(10000));
			car.validate();
		}
		
		System.out.println(TimingAspect.getTotalDurations().get("void com.revature.models.Car.validate()") / 10_000_000 + " us");
	}
}
