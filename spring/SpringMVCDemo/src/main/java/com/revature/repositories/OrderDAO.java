package com.revature.repositories;

import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.models.Order;
import com.revature.util.HibernateUtil;

@Repository
public class OrderDAO implements IOrderDAO {

	@Override
	public Set<Order> findAll() {
		Session s = HibernateUtil.getSession();
		
		Transaction tx = s.beginTransaction();
		
		Set<Order> result = s.createQuery("FROM Order o", Order.class)
				.getResultStream()
				.collect(Collectors.toSet());
		
		tx.commit();
		
		return result;
		
	}

	@Override
	public void save(Order o) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		
		s.save(o);
		
		tx.commit();
	}

	@Override
	public void update(Order o) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		
		s.merge(o);
		
		tx.commit();
	}
}
