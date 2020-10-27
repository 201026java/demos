package com.revature.models;

import org.springframework.stereotype.Component;

import com.revature.annotations.Timed;
import com.revature.exceptions.InvalidCarException;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data @NoArgsConstructor
public class Car {

	private int id;
	private String make;
	private String model;
	private int year;
	private String engine;
	
	@Timed
	public void validate() {
		switch(this.model.toLowerCase()) {
		case "mustang":
			if(this.year < 2000) {
				throw new InvalidCarException();
			}
			break;
		default:
			System.out.println("The car is good!");
		}
	}
}
