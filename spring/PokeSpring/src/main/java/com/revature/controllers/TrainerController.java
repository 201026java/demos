package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Trainer;
import com.revature.repositories.ITrainerDAO;

@RestController
@RequestMapping("trainers")
@CrossOrigin("http://localhost:4200")
public class TrainerController {

	@Autowired
	private ITrainerDAO dao;
	
	@GetMapping
	public ResponseEntity<List<Trainer> > findAll() {
		return ResponseEntity.ok(this.dao.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Trainer> findById(@PathVariable("id") int id) {
		Trainer t = this.dao.findById(id);
		
		if(t == null) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(t);
	}
	
	@PostMapping
	public ResponseEntity<List<Trainer> > insert(@RequestBody Trainer t) {
		if(t.gettId() != 0) {
			return ResponseEntity.badRequest().build();
		}
		
		this.dao.insert(t);
		
		if(t.gettId() == 0) {
			// Failed to properly save
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.ok(this.dao.findAll());
	}
}
