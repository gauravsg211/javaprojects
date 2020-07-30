package com.example.springkafka.runner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Component
@EnableRetry
public class JobRunner {

    private static final Logger logger = LoggerFactory.getLogger(JobRunner.class);
    public static final String FILE_NAME_CONTEXT_KEY = "fileName";
    int counter =0;
    final static String location = "src\\main\\resources\\employees.csv";


    private JobLauncher simpleJobLauncher;
    private Job demo1;

    @Autowired
    public JobRunner(Job demo1, JobLauncher jobLauncher) {
        this.simpleJobLauncher = jobLauncher;
        this.demo1 = demo1;
    }
    
    @Autowired
    RetryTemplate retryTemplate;
    @Autowired
    ResourceLoader resourceLoader;  
    


 @Async 
 @Retryable(value = { ItemStreamException.class} , maxAttempts = 5,backoff = @Backoff(delay =4000))
 public void runBatchJob() throws Exception,ItemStreamException{
    	
	 Resource resource = resourceLoader.getResource("classpath:employees.csv");
	 if(resource.exists()==false) {
     	counter++;
     logger.info("File Reading Service Failed "+ counter);
  
    throw new ItemStreamException("error occurred!! files doesn't exist in the specified path");
     }
	 
	 else {
    	
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString(FILE_NAME_CONTEXT_KEY, "employees.csv");
        jobParametersBuilder.addDate("date", new Date(), true);
        runJob(demo1, jobParametersBuilder.toJobParameters());
        
	 }
        
        }
    	

    public void runJob(Job job, JobParameters parameters) {
        try {
            JobExecution jobExecution = simpleJobLauncher.run(job, parameters);
        } catch (JobExecutionAlreadyRunningException e) {
            logger.info("Job with fileName={} is already running.", parameters.getParameters().get(FILE_NAME_CONTEXT_KEY));
        } catch (JobRestartException e) {
            logger.info("Job with fileName={} was not restarted.", parameters.getParameters().get(FILE_NAME_CONTEXT_KEY));
        } catch (JobInstanceAlreadyCompleteException e) {
            logger.info("Job with fileName={} already completed.", parameters.getParameters().get(FILE_NAME_CONTEXT_KEY));
        } catch (JobParametersInvalidException e) {
            logger.info("Invalid job parameters.", parameters.getParameters().get(FILE_NAME_CONTEXT_KEY));
        }
    }


}