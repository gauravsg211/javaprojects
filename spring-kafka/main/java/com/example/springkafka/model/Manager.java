package com.example.springkafka.model;

public class Manager {
	
	public int managerId;

	public String firstName;
	public String lastName;
	public String email;
	public String dept;
	
	public Manager(int managerId, String firstName, String lastName, String email, String dept) {
		super();
		this.managerId = managerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dept = dept;
	}
	public Manager()
	{
		
	}
	
	@Override
	public String toString() {
		return "Manager [managerId=" + managerId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", dept=" + dept + "]";
	}


}

