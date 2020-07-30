package com.example.springkafka.config;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.EnableAsync;

import com.example.springkafka.step.*;
import com.example.springkafka.mapper.*;
import com.example.springkafka.model.*;


@Configuration
@EnableBatchProcessing
@EnableAsync
public class BatchConfig {
	 private static final Logger logger = LoggerFactory.getLogger(BatchConfig.class);
	 int counter =0;
	    
	 
	@Autowired
	public JobRepository jobRepository;
@Autowired
    private JobBuilderFactory jobBuilderFactory;
@Autowired
    private StepBuilderFactory stepBuilderFactory;

@Autowired
private Writer writer;
@Autowired
Processor processor;

@Autowired
ResourceLoader resourceLoader;  


final static String location = "src\\main\\resources\\employees.csv";



  @Bean
    public JobLauncher simpleJobLauncher() {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        return jobLauncher;
    }
    
    @Bean
    public Job demo1Job() throws Exception {
        return this.jobBuilderFactory.get("demo1")
                .start(step1Demo1())
                .build();
    }
    
  
    @Bean
    
    public Step step1Demo1() throws Exception,ItemStreamException {
        return this.stepBuilderFactory.get("step1")
                .<Employee, Employee>chunk(5)
                .reader(Reader())
                .processor(processor)
                .writer(writer)
              
               
                .build();
    }
    
  
    @Autowired
	 private RetryTemplate retryTemplate;
    
    
    @Bean
    @StepScope
    Resource inputFileResource(@Value("#{jobParameters[fileName]}") final String fileName) throws Exception {
        return new ClassPathResource(fileName);
    }

    @Bean
    
    @StepScope
    public FlatFileItemReader<Employee> Reader() throws Exception,ItemStreamException{
    	
    	 Resource resource = resourceLoader.getResource("classpath:employees.csv");
    	
    	
    
			FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();
	        reader.setResource(inputFileResource(null));
	        reader.setLineMapper(new DefaultLineMapper<Employee>() {{
	            setLineTokenizer(new DelimitedLineTokenizer() {{
	                setNames("employeeId", "firstName", "lastName", "age", "email");
	                setDelimiter(",");
	            }});
	            setFieldSetMapper(new EmployeeFileRowMapper());
	            
	        }});
	       
	   	 if(resource.exists()==true) {

	        
			 return reader;
	        
			}
	        else throw new ItemStreamException("Exception");
    	}
	        
	        
	       
    	
    	
    	 
    
	
      
        
    }
   
    
   
    
    
    
    
    

    

    
   
    
   
   
        
    
    
    

    
    



