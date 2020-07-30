package com.javatechie.spring.cassandra.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.spring.cassandra.api.model.User;
import com.javatechie.spring.cassandra.api.rpository.UserRepository;
import com.javatechie.spring.cassandra.api.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	private UserRepository repository;
	@Autowired
	CassandraTemplate cassandraTemplate;
	
	
	

	
	 @PostMapping("/addUser")
	    public User addCity(@RequestBody User user){
	        return userService.addUser(user);
	    }

	@GetMapping("/getAllUsers")
	public List<User> getUsers() {
		return cassandraTemplate.select("select * from User", 	User.class);
	}

	@GetMapping("/getUserFilterByAge/{age}")
	public List<User> getUserFilterByAge(@PathVariable int age) {
		String query1="select * from User where age>="+String.valueOf(age);
		if(true) {
		query1=query1+" ALLOW FILTERING";
		
		}
		
		
		return cassandraTemplate.select(query1, 	User.class);
	}
}
