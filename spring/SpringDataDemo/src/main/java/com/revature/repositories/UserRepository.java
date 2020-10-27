package com.revature.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.revature.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUsername(String username);
	public List<User> findByFirstName(String firstName);
	public List<User> findByFirstNameStartsWith(String prefix);
	public List<User> findByFirstNameEndsWith(String suffix);
	public List<User> findByFirstNameContains(String substr);
	
	@Transactional
	@Modifying
	@Query(value = "FROM User WHERE email LIKE %:substr%")
	public List<User> findByEmailContains(String substr);
}
