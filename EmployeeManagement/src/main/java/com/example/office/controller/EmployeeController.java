package com.example.office.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.office.dto.EmployeeModelDTO;
import com.example.office.entity.Employee;
import com.example.office.service.EmployeeService;
@RestController
@RequestMapping("/Employee")
public class EmployeeController {

	
	@Autowired
    private EmployeeService employeeService;
	
	 @GetMapping
	    public ResponseEntity<List<Employee>> getAllEmployees() {
	 
	        List<Employee> employees = employeeService.getAllEmployees();
	        return new ResponseEntity<>(employees, HttpStatus.OK);
	    }
	 
	 @PostMapping()
		public EmployeeModelDTO addEmployee(@RequestBody EmployeeModelDTO  employeeModelDTO ){
			return employeeService.addEmployee(employeeModelDTO);
		}
}
