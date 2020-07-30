package com.example.office.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.office.entity.Manager;
import com.example.office.exception.ManagerIdNotFoundException;

import com.example.office.dto.ManagerModelDTO;
import com.example.office.entity.Employee;


import java.util.List;
import java.util.Optional;

import com.example.office.repository.ManagerRepository;

@Service
public class ManagerService {
	
        @Autowired
	    private ManagerRepository managerRepository;
        
        public ResponseEntity<Object> addManager(Manager manager) {
        	
        	Manager mng = managerRepository.findById(manager.getManagerId()).orElse(null);
            if ( mng==null) {
   		 
        	for(Employee emp:manager.getReportingEmployees()) {
        		
        		emp.setManager(manager);
        	}
        	
         Manager savedManager = managerRepository.save(manager);
       
            if (managerRepository.findById(savedManager.getManagerId()).isPresent()) {
                return ResponseEntity.accepted().body("Successfully Created Managers and Reporting employees");
            } 
            else
            {
                return ResponseEntity.unprocessableEntity().body("Failed to Create specified Manager");}
            
            }
        	 else
             {
             return ResponseEntity.unprocessableEntity().body("Cant create a new Manager, a manager with EmailID:'"+manager.getEmail()+"' alredy esists with ManagerId:"+manager.getManagerId());
            
             }
        }
         
        public List<Manager> getAllManagers() {
		 
	        return managerRepository.findAll();
	    }
        
        public ManagerModelDTO getManagerById(int managerId) {
    		Optional<Manager> managerObj = managerRepository.findById(managerId);
    		if(managerObj.isPresent()) {
    			return new ManagerModelDTO(managerObj.get());
    		}
    		else
    		{
    			throw new ManagerIdNotFoundException("Manager Id not present");
    		
    		}
    	}

        
        public Manager updateManager(Manager manager, int managerId) throws ManagerIdNotFoundException {
   		
	        Manager mng=managerRepository.findById(managerId).orElse(null);
	        if(mng!=null) {
	        	
	        	mng.setManager_First_Name(manager.getManager_First_Name());
	        	mng.setManager_Last_Name(manager.getManager_Last_Name());
	        	mng.setAddress(manager.getAddress());
	        	mng.setDept(manager.getDept());
	        	mng.setEmail(manager.getEmail());
	        	mng.setSalary(manager.getSalary());
	        	mng.setJoiningDateTime(manager.getJoiningDateTime());
	        	
	        	return managerRepository.save(mng);
	        }
	    	
	    	
	    	else throw  new ManagerIdNotFoundException("ManagerId is not found");
	    
        }
        
        
        public ResponseEntity<Object> deleteManagerById(int id) {
    		Optional<Manager> manager =managerRepository.findById(id);
    		if (!manager.isPresent()) {
    			throw new  ManagerIdNotFoundException("ManagerId is not found");
    		}
    	
    		managerRepository.deleteById(id);
    		
    		Manager m=managerRepository.findById(id).orElse(null);
    		if(m==null)
    		{
    			 return ResponseEntity.accepted().body("Manager with Id "+id+" is succesfully deleted.");
    		}
    		
    		else
    			return ResponseEntity.unprocessableEntity().body("Failed to Create specified Manager");
    	}
        
}
    		
	    

