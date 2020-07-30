package com.example.springBatch.config;
import java.util.Date;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

 

import com.example.springBatch.listener.JobCompletionListener;
import com.example.springBatch.step.Processor;
import com.example.springBatch.step.Reader;
import com.example.springBatch.step.Writer;

 

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {
    
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    private SimpleJobLauncher simpleJobLauncher;
    @Autowired
    Job processJob;
    
    
    @Bean
    public Job processJob() {
        return jobBuilderFactory.get("processJob")
                .incrementer(new RunIdIncrementer()).listener(listener())
                .flow(orderStep1()).end().build();
    }
    
    @Bean
    public Step orderStep1() {
        return stepBuilderFactory.get("orderStep1").<String, String> chunk(1)
                .reader(new Reader())
                .processor(new Processor())
                .writer(new Writer()).build();
    }

 

    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionListener();
    }
    
   @Scheduled(cron = "*/5 * * * * *")
    public void perform() throws Exception {
System.out.println("Job Started at :" + new Date());
JobParameters param = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
JobExecution execution = simpleJobLauncher.run(processJob, param);
  System.out.println("Job finished with status :" + execution.getStatus());
    }
   
   
   @Bean
   public ResourcelessTransactionManager transactionManager() {
       return new ResourcelessTransactionManager();
   }    
   @Bean
   public MapJobRepositoryFactoryBean mapJobRepositoryFactory(ResourcelessTransactionManager txManager)
           throws Exception {



       MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean(txManager);



       factory.afterPropertiesSet();



       return factory;
   }
   @Bean
   public JobRepository jobRepository(MapJobRepositoryFactoryBean factory) throws Exception {
       return factory.getObject();
   }

   @Bean
   public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
       SimpleJobLauncher launcher = new SimpleJobLauncher();
       launcher.setJobRepository(jobRepository);
       return launcher;
   }
   




}
    
    
    
