package com.example.office.entity;

import java.util.List;
import java.util.stream.Collectors;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import com.example.office.dto.ManagerModelDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="Manager")
@EntityListeners(AuditingEntityListener.class)

public class Manager {
	@Id
	@JsonProperty("ManagerId")
    private int managerId;
	
   @Column(name = "MANAGER_FIRST_NAME", length = 30, nullable = false, unique = false)
   @JsonProperty("Manager First Name")
   private String manager_First_Name;
	
	@Column(name = "MANAGER_LAST_NAME", length = 30, nullable = true, unique = false)
	@JsonProperty("Manager Last Name")
    private String manager_Last_Name;
	
	@Column(name = "EMAIL", length = 30, nullable = false, unique = true)
   private String email;
	
	@Column(name = "SALARY", length = 30, nullable = true, unique = false)
	@JsonProperty("Salary")
   private Float salary;
	
	@Column(name = "JOINING_DATE_TIME", length = 30, nullable = false, unique = false)
	@JsonProperty("JoiningDateTime")
   private Timestamp joiningDateTime;
	
	@Column(name = "DEPARTMENT", length = 30, nullable = false, unique = false)
	@JsonProperty("Dept")
   private String dept;
	
    @Column(name = "ADDRESS", length = 30, nullable = false, unique = false)
    @JsonProperty("Address")
    private String address;
    
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="manager")
  @JsonProperty("ReportingEmployees")
	private List<Employee> reportingEmployees;
  
	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getManager_First_Name() {
		return manager_First_Name;
	}

	public void setManager_First_Name(String manager_First_Name) {
		this.manager_First_Name = manager_First_Name;
	}

	public String getManager_Last_Name() {
		return manager_Last_Name;
	}

	public void setManager_Last_Name(String manager_Last_Name) {
		this.manager_Last_Name = manager_Last_Name;
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

	public Timestamp getJoiningDateTime() {
		return joiningDateTime;
	}

	public void setJoiningDateTime(Timestamp joiningDateTime) {
		this.joiningDateTime = joiningDateTime;
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
	@JsonBackReference
	public List<Employee> getReportingEmployees() {
		return reportingEmployees;
	}

	public void setReportingEmployees(List<Employee> reportingEmployees) {
		this.reportingEmployees = reportingEmployees;
	}
	
	public Manager() {
		
	}
	
	public Manager(ManagerModelDTO managerModeldto) {
		
	
		this.managerId = managerModeldto.getManagerId();
		this.manager_First_Name = managerModeldto.getFirstName();
		this.manager_Last_Name = managerModeldto.getLastName();
		this.email = managerModeldto.getEmail();
		this.salary = managerModeldto.getSalary();
		this.joiningDateTime = managerModeldto.getJoiningDateTime();
		this.dept = managerModeldto.getDept();
		this.address = managerModeldto.getAddress();
		this.reportingEmployees=managerModeldto.getReportingEmployees().stream().map((x)->new Employee(x)).collect(Collectors.toList());
		
	}

}