package com.example.springboottesting.controller;

import com.example.springboottesting.domain.User;
import com.example.springboottesting.service.UserService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	//@GetMapping("user/{userName}")
	@GetMapping("user")
	//public User getUserByName(@PathVariable(value = "userName") String userName) {
	public User getUserByName(@Param(value = "UserName") String userName) {
		return this.userService.findByName(userName);
	}

	@PostMapping("user/save")
	public void saveUser(@RequestBody User user) {
		this.userService.saveUser(user);
	}
}