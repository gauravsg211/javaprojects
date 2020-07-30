package com.example.office.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.office.dto.ManagerModelDTO;
import com.example.office.entity.Manager;
import com.example.office.service.ManagerService;
@RestController
@RequestMapping("/Manager")
public class ManagerController {
	
	@Autowired
    private ManagerService managerService;

	
	 @PostMapping
	    public ResponseEntity<Object> addManager(@RequestBody Manager manager ) {
	        return  managerService.addManager(manager);
	    }
	 
	 @GetMapping("/{id}")
	    public ManagerModelDTO getAllEmployees(@PathVariable int id) {
	 
	       return managerService.getManagerById(id);
	        
	    }
	 
	 @PutMapping("update/{managerId}")
		public Manager updateManager(@PathVariable("managerId")int managerId,@RequestBody Manager manager ) {

		
				return managerService.updateManager(manager,managerId);
		}
	 
	 @GetMapping
	    public ResponseEntity<List<Manager>> getAllManagers() {
	 
	        List<Manager> managers = managerService.getAllManagers();
	        return new ResponseEntity<>(managers, HttpStatus.OK);
	    }
	//delete manager by id
		@DeleteMapping("/{id}")
		public  ResponseEntity<Object> deleteManagerById(@PathVariable("id") int  id) {
			return managerService.deleteManagerById(id);
		}
	 
}
