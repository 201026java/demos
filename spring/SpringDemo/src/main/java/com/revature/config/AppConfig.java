package com.revature.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.revature.beans.Account;
import com.revature.beans.User;

@Configuration
@ComponentScan("com.revature")
public class AppConfig {

	@Bean(name = "user")
	public User getUser() {
		return new User();
	}
	
	@Bean(name = "account")
	public Account getAccount() {
		return new Account();
	}
}
