package com.example.springkafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${manager.topic.name}")
    private String managerTopicName;
    
    @Value(value = "${employee.topic.name}")
    private String employeeTopicName;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

 

    @Bean
    public NewTopic topic4() {
        return new NewTopic(managerTopicName, 1, (short) 1);
    }
    
    @Bean
    public NewTopic topic5() {
        return new NewTopic(employeeTopicName, 1, (short) 1);
    }
}
