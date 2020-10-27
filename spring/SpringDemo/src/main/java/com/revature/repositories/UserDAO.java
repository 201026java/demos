package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.revature.beans.Account;
import com.revature.beans.User;

@Repository
public class UserDAO implements IUserDAO {
	
	private List<User> list = new ArrayList<>();
	
	public UserDAO() {
		super();
		list.add(new User(1, "John", "Smith", "Mordecai", "Rigby", new Account(1, 1_000_000)));
		list.add(new User(2, "Rebecca", "Brown", "becca", "password", new Account(2, 0)));
	}

	@Override
	public List<User> findAll() {
		return list;
	}
}
