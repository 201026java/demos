package com.revature.repositories;

import java.util.List;

import com.revature.beans.User;

public interface IUserDAO {
	
	public List<User> findAll();
}
