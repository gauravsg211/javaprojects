package com.example.office.dto;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.example.office.entity.Manager;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ManagerModelDTO {
	
	private int managerId;
	@JsonProperty("Manager First Name")
	private String firstName;
	@JsonProperty("Manager Last Name")
	private String lastName;
	private String email;
	@JsonProperty("Salary")
	private Float salary;
	@JsonProperty("JoiningDateTime")
	private Timestamp joiningDateTime;
	public Timestamp getJoiningDateTime() {
		return joiningDateTime;
	}
	public void setJoiningDateTime(Timestamp joiningDateTime) {
		this.joiningDateTime = joiningDateTime;
	}

	@JsonProperty("Dept")
	private String dept;
	@JsonProperty("Address")
	private String address;
	@JsonProperty("ReportingEmployees")
	private List<EmployeeModelDTO> reportingEmployees;
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<EmployeeModelDTO> getReportingEmployees() {
		return reportingEmployees;
	}
	public void setReportingEmployees(List<EmployeeModelDTO> reportingEmployees) {
		this.reportingEmployees = reportingEmployees;
	}


	public ManagerModelDTO() {}
	
	public ManagerModelDTO(Manager manager) {
		
			this.managerId = manager.getManagerId();
			
		this.firstName = manager.getManager_First_Name();
		this.lastName = manager.getManager_Last_Name();
		this.email = manager.getEmail();
		this.salary = manager.getSalary();
		this.joiningDateTime = manager.getJoiningDateTime();
		this.dept = manager.getDept();
		this.address = manager.getAddress();
		this.reportingEmployees=manager.getReportingEmployees().stream().map((x)->new EmployeeModelDTO(x)).collect(Collectors.toList());
	}
	
	public ManagerModelDTO setManagerDTO(Manager manager) {
		ManagerModelDTO m=new ManagerModelDTO();
		
			m.managerId = manager.getManagerId();
			
		m.firstName = manager.getManager_First_Name();
		m.lastName = manager.getManager_Last_Name();
		m.email = manager.getEmail();
		m.salary = manager.getSalary();
		m.joiningDateTime = manager.getJoiningDateTime();
		m.dept = manager.getDept();
		m.address = manager.getAddress();
	return m;
	}
	
}