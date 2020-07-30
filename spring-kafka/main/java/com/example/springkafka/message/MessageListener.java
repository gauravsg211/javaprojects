package com.example.springkafka.message;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;

import com.example.springkafka.config.KafkaConsumerConfig;
import com.example.springkafka.model.Employee;
import com.example.springkafka.model.Manager;

@Service
public  class MessageListener {


    public CountDownLatch managerLatch = new CountDownLatch(1);
    
    public CountDownLatch employeeLatch = new CountDownLatch(1);
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;
    
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerConfig.class);
    

    @KafkaListener(topics = "${manager.topic.name}", containerFactory = "managerKafkaListenerContainerFactory")
    public void managerListener(Manager manager) {
        System.out.println("Recieved manager message: " + manager);
        this.managerLatch.countDown();
    }
    
    @KafkaListener(topics = "${employee.topic.name}", containerFactory = "employeeKafkaListenerContainerFactory")
    public void employeeListener(Employee employee ) {
    	
    	
    	Properties properties = new Properties();
    	properties.put("bootstrap.servers", bootstrapAddress);
    	properties.put("connections.max.idle.ms", 10000);
    	properties.put("request.timeout.ms", 5000);
    	try (AdminClient client = KafkaAdminClient.create(properties))
    	{
    	    ListTopicsResult topics = client.listTopics();
    	    Set<String> names = topics.names().get();
    	    if (!names.isEmpty())
    	    {
    	    	System.out.println("Recieved employee message: " + employee);
    	        this.managerLatch.countDown();
    	    }
    	    
    	}
    	catch (InterruptedException | ExecutionException e)
    	{
    	    logger.info("kafka server is down");
    	}
        
    }

}