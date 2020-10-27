package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Pokemon;
import com.revature.models.Trainer;
import com.revature.repositories.IPokemonDAO;
import com.revature.repositories.ITrainerDAO;

@RestController
@RequestMapping("pokemon")
@CrossOrigin("http://localhost:4200")
public class PokemonController {

	@Autowired
	private IPokemonDAO pokeDao;
	
	@Autowired
	private ITrainerDAO trainerDao;
	
	@PostMapping
	public ResponseEntity<List<Trainer> > insert(@RequestBody Pokemon p) {
		if(p.getPokeId() != 0) {
			return ResponseEntity.badRequest().build();
		}
		
		this.pokeDao.insert(p);
		
		if(p.getPokeId() == 0) {
			// Failed to properly save
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.ok(this.trainerDao.findAll());
	}
}
