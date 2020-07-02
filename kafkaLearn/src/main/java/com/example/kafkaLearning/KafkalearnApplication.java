package com.example.kafkaLearning;

import java.util.concurrent.TimeUnit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import com.example.kafkaLearning.model.*;
import com.example.kafkaLearning.message.*;

@SpringBootApplication
public class KafkalearnApplication {

    public static void main(String[] args) throws Exception {

         SpringApplication.run(KafkalearnApplication.class, args);

        
    
}
}

