package com.revature.repositories;

import java.util.Set;

import com.revature.models.Pizza;

public interface IPizzaDAO {

	public Set<Pizza> findAll();
	
	public void save(Pizza p);
	
	public void update(Pizza p);
}
