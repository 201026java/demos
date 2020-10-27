package com.revature.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Order;
import com.revature.models.Pizza;
import com.revature.repositories.IOrderDAO;

@Service
public class OrderService {
	
	@Autowired
	private IOrderDAO dao;
	
	public void complete(Order o) {
		o.setTotal(o.calculateTax());
	}
	
	public Set<Order> findAll() {
		return dao.findAll();
	}
	
	public void save(Order o) {
		dao.save(o);
	}
	
	public void update(Order o) {
		dao.update(o);
	}
	
	public void addPizza(Order o, Pizza p) {
		o.getItems().add(p);
	}
}
