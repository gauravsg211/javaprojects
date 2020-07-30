package com.example.office.dto;


import com.fasterxml.jackson.annotation.JsonProperty;



import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.office.entity.Employee;

public class EmployeeModelDTO {
	private int id;
	private String firstName;
	private String lastName;
	private String middleName;
	private String email;
	private long  phoneNumber;
	private String address1;
	private String city;
	@JsonProperty("Dept")
	private String dept;
	private String state;
	private int postalCode;
	@JsonProperty("Salary")
	private Float salary;
	@JsonProperty("ManagerId")
	private int managerId;
	
	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
public EmployeeModelDTO(Employee emp) {
		
	
	
		this.id = emp.getId();
	
		this.firstName = emp.getFirstName();
		this.lastName = emp.getLastName();
		this.middleName = emp.getMiddleName();
		this.email = emp.getEmail();
		this.phoneNumber = emp.getPhoneNumber();
		this.address1 = emp.getAddress1();
		this.city = emp.getCity();
		this.dept = emp.getDept();
		this.state = emp.getState();
		this.postalCode = emp.getPostalCode();
		this.salary = emp.getSalary();
		
		
	}
	
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public EmployeeModelDTO() {}
	
	public EmployeeModelDTO setEmployeeModelDTO(Employee emp) {
		EmployeeModelDTO empDTO=new EmployeeModelDTO();
		
		
			empDTO.setId(emp.getId());
		
		
		empDTO.setFirstName(emp.getFirstName());
		empDTO.setLastName(emp.getLastName());
		empDTO.setMiddleName(emp.getMiddleName());
		empDTO.setEmail(emp.getEmail());
		empDTO.setPhoneNumber(emp.getPhoneNumber());
		empDTO.setAddress1(emp.getAddress1());
		empDTO.setCity(emp.getCity());
		empDTO.setDept(emp.getDept());
		empDTO.setState(emp.getState());
		empDTO.setPostalCode(emp.getPostalCode());
		empDTO.setSalary(emp.getSalary());
		empDTO.setManagerId(emp.getManager().getManagerId());
		
		return empDTO;
	}
}
