package com.revature.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.models.Pizza;
import com.revature.services.PizzaService;

@Controller
public class PizzaController {
	
	@Autowired
	private PizzaService service;

	@GetMapping(path = "pizza", produces = "application/json")
	@ResponseBody
	public ResponseEntity< Set<Pizza> > findAll() {
		Set<Pizza> result = service.findAll();
		
		if(result.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("pizza/{id}")
	@ResponseBody
	public ResponseEntity<Pizza> findById(@PathVariable("id") int id) {
		Set<Pizza> all = service.findAll();
		
		for(Pizza p : all) {
			if(p.getId() == id) {
				return ResponseEntity.ok(p);
			}
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("pizza")
	@ResponseBody
	public ResponseEntity<Pizza> insert(@RequestBody Pizza p) {
		if(p.getId() != 0) {
			return ResponseEntity.badRequest().build();
		}
		
		service.save(p);
		
		if(p.getId() == 0) {
			// Failed to insert properly
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.accepted().body(p);
	}
}
