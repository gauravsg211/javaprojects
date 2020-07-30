package com.javatechie.spring.cassandra.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.spring.cassandra.api.rpository.UserRepository;
import com.javatechie.spring.cassandra.api.model.*;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User addUser(User user)
	{
	return repository.save(user);
	}
	
	
}
	
	

