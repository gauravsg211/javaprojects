package com.example.office.entity;

import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import com.example.office.dto.EmployeeModelDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="Employee")
@EntityListeners(AuditingEntityListener.class)

public class Employee  {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	@Column(name = "FIRST_NAME", length = 30, nullable = false, unique = false)
	@JsonProperty("firstName")
    private String firstName;
	@Column(name = "LAST_NAME", length = 30, nullable = true, unique = false)
	@JsonProperty("lastName")
	private String lastName;
	@Column(name = "MIDDLE_NAME", length = 30, nullable = true, unique = false)
	@JsonProperty("middleName")
	private String middleName;
	@Column(name = "EMAIL", length = 30, nullable = false, unique = true)
	@JsonProperty("email")
	private String email;
	@Column(name = "PHONE_NUMBER", length = 12, nullable = false, unique = true)
	@JsonProperty("phoneNumber")
	private long phoneNumber;
	@Column(name = "ADDRESS1", length = 50, nullable = false, unique = false)
	@JsonProperty("address1")
	private String address1;
	@Column(name = "CITY", length = 50, nullable = false, unique = false)
	@JsonProperty("city")
	private String city;
	@Column(name = "DEPARTMENT", length =30, nullable = false, unique = false)
	@JsonProperty("Dept")
	private String dept;
	@Column(name = "STATE", length =30, nullable = false, unique = false)
	@JsonProperty("state")
	private String state;
	@Column(name = "POSTAL_CODE", length =30, nullable = false, unique = false)
	@JsonProperty("postalCode")
	private int postalCode;
	@Column(name = "SALARY", length =10, nullable = false, unique = false)
	@JsonProperty("Salary")
	private Float salary;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="manager_id",referencedColumnName= "managerId" )
	private Manager manager;



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
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
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
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
public Employee() {}
	
	public Employee(EmployeeModelDTO empDTO) {
		
		this.id = empDTO.getId();
		
		this.firstName = empDTO.getFirstName();
		this.lastName = empDTO.getLastName();
		this.middleName = empDTO.getMiddleName();
		this.email = empDTO.getEmail();
		this.phoneNumber = empDTO.getPhoneNumber();
		this.address1 = empDTO.getAddress1();
		this.city = empDTO.getCity();
		this.dept = empDTO.getDept();
		this.state = empDTO.getState();
		this.postalCode = empDTO.getPostalCode();
		this.salary = empDTO.getSalary();
	}
	
	
}
