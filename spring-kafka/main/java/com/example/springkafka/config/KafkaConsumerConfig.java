package com.example.springkafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.retry.support.RetryTemplate;

import com.example.springkafka.model.Employee;
import com.example.springkafka.model.Manager;


@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerConfig.class);

   public ConsumerFactory<String, Manager> managerConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "manager");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Manager.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Manager> managerKafkaListenerContainerFactory(RetryTemplate retryTemplate) {
        ConcurrentKafkaListenerContainerFactory<String, Manager> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(managerConsumerFactory());
        factory.setRetryTemplate(retryTemplate);
        factory.setRecoveryCallback(context -> {
          logger.info("RetryPolicy limit has been exceeded! You should really handle this better.");
          return null;
        });
        return factory;
    }
    public ConsumerFactory<String, Employee> employeeConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "employee");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Employee.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Employee> employeeKafkaListenerContainerFactory (RetryTemplate retryTemplate)throws Exception {
        ConcurrentKafkaListenerContainerFactory<String, Employee> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(employeeConsumerFactory());
        factory.setRetryTemplate(retryTemplate);
        factory.setRecoveryCallback(context -> {
          logger.info("RetryPolicy limit has been exceeded! You should really handle this better.");
          throw new Exception("kafka server is down");
        });
        return factory;
    }

}