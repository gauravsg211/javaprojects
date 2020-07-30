package com.javatechie.spring.cassandra.api.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;



@Table
public class User {

	@PrimaryKey
	private int id;
	private String name;
	private String address;
	private int age;
	private String city;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp created_at;
	
	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		 
       
        
        this.created_at=created_at;
        
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public User(int id, String name, String address, int age, String city,Timestamp created_at) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.age = age;
		this.city=city;
		this.created_at=created_at;
	}
	
	public User() {
		
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	

}
