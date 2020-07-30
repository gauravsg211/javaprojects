package com.example.springkafka.step;


import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.example.springkafka.message.MessageListener;
import com.example.springkafka.message.MessageProducer;
import com.example.springkafka.model.*;

@Component
public class Writer implements ItemWriter<Employee> {

	@Autowired
	MessageListener messageListener;
	
	@Autowired
	MessageProducer messageProducer;

    @Override
    public void write(List<? extends Employee> employees) throws Exception {
    	for(Employee e:employees) {
    	messageProducer.sendEmployeeMessage(e);
		messageListener.employeeLatch.await(10, TimeUnit.SECONDS);
	
    	}
		
        System.out.println("inside writer " + employees);
    }
}

