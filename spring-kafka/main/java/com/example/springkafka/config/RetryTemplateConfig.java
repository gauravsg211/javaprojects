package com.example.springkafka.config;


import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemStreamException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration

@EnableRetry

public class RetryTemplateConfig {


    @Bean
    

    public RetryTemplate retryTemplate() {
    	
    	int maxAttempts=5;
    	Map<Class<? extends Throwable>, Boolean> exceptions = new HashMap<>();
        exceptions.put(ItemStreamException.class, true);
       

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(maxAttempts,exceptions);

     
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();

        backOffPolicy.setBackOffPeriod(5000);

    RetryTemplate template = new RetryTemplate();

        template.setRetryPolicy(retryPolicy);

        template.setBackOffPolicy(backOffPolicy);

        return template;

    }

}





