package com.example.employee.repository;

import com.example.employee.modal.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EmployeeRepository extends JpaRepository <Employee, Long>
{

	
}
 