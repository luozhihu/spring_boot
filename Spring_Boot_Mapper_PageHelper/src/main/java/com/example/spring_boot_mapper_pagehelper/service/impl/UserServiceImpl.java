package com.example.spring_boot_mapper_pagehelper.service.impl;

import com.example.spring_boot_mapper_pagehelper.bean.User;
import com.example.spring_boot_mapper_pagehelper.service.UserService;
import org.springframework.stereotype.Repository;

@Repository("userService")
public class UserServiceImpl extends BaseService<User> implements UserService {
	
}