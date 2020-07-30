package com.example.office.repository;
import com.example.office.entity.Manager;

import org.springframework.data.jpa.repository.JpaRepository;
public interface ManagerRepository extends JpaRepository<Manager, Integer>{
	
}
