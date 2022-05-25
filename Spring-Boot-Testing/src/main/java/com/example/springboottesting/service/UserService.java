package com.example.springboottesting.service;

import com.example.springboottesting.domain.User;

public interface UserService extends IService<User>{
	User findByName(String userName);
	
	void saveUser(User user);
}