package com.revature.repositories;

import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.models.Pizza;
import com.revature.util.HibernateUtil;

@Repository
public class PizzaDAO implements IPizzaDAO {

	@Override
	public Set<Pizza> findAll() {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		
		Set<Pizza> result = s.createQuery("FROM Pizza p", Pizza.class)
				.getResultStream()
				.collect(Collectors.toSet());
		
		tx.commit();
		
		return result;
	}

	@Override
	public void save(Pizza p) {
		Session s = HibernateUtil.getSession();
		
		Transaction tx = s.beginTransaction();
		
		s.save(p);
		
		tx.commit();
	}

	@Override
	public void update(Pizza p) {
		Session s = HibernateUtil.getSession();
		
		Transaction tx = s.beginTransaction();
		
		s.merge(p);
		
		tx.commit();
	}
}
