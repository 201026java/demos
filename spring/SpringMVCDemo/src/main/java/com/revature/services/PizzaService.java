package com.revature.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Pizza;
import com.revature.repositories.IPizzaDAO;

@Service
public class PizzaService {
	
	@Autowired
	private IPizzaDAO dao;
	
	public Set<Pizza> findAll() {
		return dao.findAll();
	}
	
	public void save(Pizza p) {
		dao.save(p);
	}
	
	public void update(Pizza p) {
		dao.update(p);
	}
}
