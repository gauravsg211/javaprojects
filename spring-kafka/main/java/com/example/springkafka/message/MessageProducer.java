package com.example.springkafka.message;

import org.springframework.batch.item.ItemStreamException;
import java.lang.IllegalStateException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.retry.annotation.EnableRetry;

import com.example.springkafka.config.BatchConfig;
import com.example.springkafka.model.Employee;
import com.example.springkafka.model.Manager;


@Service
@EnableRetry
public  class MessageProducer {

    @Autowired
    private KafkaTemplate<String, Manager> greetingKafkaTemplate;
    @Autowired
    private KafkaTemplate<String, Employee> employeeKafkaTemplate;
    @Autowired 
    RetryTemplate retryTemplate;
    @Autowired
    ResourceLoader resourceLoader;  
    
   
    
    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);
    int counter =0;
    final static String location = "src\\main\\resources\\employees.csv";

    @Value(value = "${manager.topic.name}")
    private String managerTopicName;
    
    @Value(value = "${employee.topic.name}")
    private String employeeTopicName;

   
    public void sendManagerMessage(Manager manager) {
        greetingKafkaTemplate.send(managerTopicName, manager);
    }
    
    
    
    public void sendEmployeeMessage(Employee employee)throws ItemStreamException,IllegalStateException {
    	 Resource resource = resourceLoader.getResource("classpath:employees.csv");
    	 if(resource.exists()==true) {
    		employeeKafkaTemplate.send(employeeTopicName, employee);
    	}
    	else  retryTemplate.execute(context -> {
   		 
            verifyIfFileExist();
                     return true;
	
});
       
    }
    
    
    private void verifyIfFileExist() throws ItemStreamException {
        counter++;
        logger.info("File Reading Service Failed "+ counter);
     
       throw new ItemStreamException("error occurred in producer!! files doesn't exist in the specified path");
    
   	  }
   
    
  
    
}