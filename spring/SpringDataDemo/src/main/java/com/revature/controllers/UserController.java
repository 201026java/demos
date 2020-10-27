package com.revature.controllers;

import java.util.List;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.Example.PropertySelector;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository dao;

	@GetMapping
	public ResponseEntity< List<User> > findAll() {
		return ResponseEntity.ok(dao.findAll());
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User u) {
		if(dao.existsById(u.getId())) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.accepted().body(dao.save(u));
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> findByUsername(@PathVariable(name = "username") String username) {
		User u = dao.findByUsername(username);
		
		if(u == null) {
//			return ResponseEntity.noContent().build();
			throw new UserNotFoundException(username);
		}
		
		return ResponseEntity.ok(u);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") int id) {
		if(dao.existsById(id)) {
			dao.deleteById(id);
			return ResponseEntity.accepted().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/firstname/{firstname}")
	public ResponseEntity< List<User> > findByFirstName(@PathVariable(name = "firstname") String firstname) {
		List<User> list = dao.findByFirstName(firstname);
		
		if(list.isEmpty()) {
//			return ResponseEntity.noContent().build();
			throw new UserNotFoundException();
		}
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/firstnamestarts/{firstname}")
	public ResponseEntity< List<User> > findByFirstNameStarts(@PathVariable(name = "firstname") String firstname) {
		List<User> list = dao.findByFirstNameStartsWith(firstname);
		
		if(list.isEmpty()) {
//			return ResponseEntity.noContent().build();
			throw new UserNotFoundException();
		}
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/firstnameends/{firstname}")
	public ResponseEntity< List<User> > findByFirstNameEnds(@PathVariable(name = "firstname") String firstname) {
		List<User> list = dao.findByFirstNameEndsWith(firstname);
		
		if(list.isEmpty()) {
//			return ResponseEntity.noContent().build();
			throw new UserNotFoundException();
		}
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/firstnamecontains/{firstname}")
	public ResponseEntity< List<User> > findByFirstNameContains(@PathVariable(name = "firstname") String firstname) {
		List<User> list = dao.findByFirstNameContains(firstname);
		
		if(list.isEmpty()) {
//			return ResponseEntity.noContent().build();
			throw new UserNotFoundException();
		}
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/emailcontains/{email}")
	public ResponseEntity< List<User> > findByEmailContains(@PathVariable(name = "email") String email) {
		List<User> list = dao.findByEmailContains(email);
		
		if(list.isEmpty()) {
//			return ResponseEntity.noContent().build();
			throw new UserNotFoundException();
		}
		
		return ResponseEntity.ok(list);
	}
}
