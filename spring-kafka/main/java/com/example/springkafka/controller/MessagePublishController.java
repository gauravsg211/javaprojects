package com.example.springkafka.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springkafka.model.*;
import com.example.springkafka.message.*;
import com.example.springkafka.runner.*;



@RestController
@RequestMapping("/publish")
public class MessagePublishController {
	
	
	
	@Autowired
	MessageListener messageListener;
	
	@Autowired
	MessageProducer messageProducer;
	
	@Autowired
	private JobRunner jobRunner;
	

	@PostMapping("/employeeTopic")
	public String publishEmployee(@RequestBody  Employee employee) throws InterruptedException {
		messageProducer.sendEmployeeMessage(employee);
		messageListener.employeeLatch.await(10, TimeUnit.SECONDS);
		return "employee topic message published!!!!";

}
	
	@GetMapping(value = "/job")
    public String runJob() throws Exception {
        jobRunner.runBatchJob();
        return String.format("employee topic publish job started!!!!");
    }
}

