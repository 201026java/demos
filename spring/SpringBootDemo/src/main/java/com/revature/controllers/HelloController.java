package com.revature.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
// All methods within this class will automatically use @ResponseBody
// This makes sure that this Controller never sends back any static resources
public class HelloController {

	@GetMapping
	public ResponseEntity<String> helloWorld() {
		return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT)
				.body("Hello World! This is my very first Spring Boot Application!");
	}
}
