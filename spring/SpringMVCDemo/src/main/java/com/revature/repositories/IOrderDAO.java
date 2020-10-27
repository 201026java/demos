package com.revature.repositories;

import java.util.Set;

import com.revature.models.Order;

public interface IOrderDAO {

	public Set<Order> findAll();
	
	public void save(Order o);
	
	public void update(Order o);
}
