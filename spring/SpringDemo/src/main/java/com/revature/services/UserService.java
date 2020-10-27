package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.User;
import com.revature.repositories.IUserDAO;
import com.revature.repositories.UserDAO;

@Service
public class UserService implements IUserService {
	
//	@Autowired
	private IUserDAO dao;
	
	public UserService() {
		super();
		System.out.println("Inside no-arg Constructor for UserService");
	}

	public UserService(IUserDAO dao) {
		super();
		this.dao = dao;
		System.out.println("Inside 1-arg Constructor for UserService");
	}

	public IUserDAO getDao() {
		return dao;
	}

	@Autowired
	public void setDao(IUserDAO dao) {
		this.dao = dao;
		System.out.println("Inside setter for UserService");
	}

	@Override
	public List<User> findAll() {
		return dao.findAll();
	}
}
