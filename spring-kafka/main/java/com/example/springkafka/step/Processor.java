package com.example.springkafka.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.springkafka.model.*;
@Component
public class Processor implements ItemProcessor<Employee, Employee> {

    private static final Logger log = LoggerFactory.getLogger(Processor.class);

    @Override
    public Employee process(final Employee employee) throws Exception {
  
        final String firstName = employee.getFirstName().toUpperCase();
        final String lastName = employee.getLastName().toUpperCase();

        final Employee transformedEmployee = new Employee(employee.getEmployeeId(),firstName, lastName,employee.getAge(),employee.getEmail());

        log.info("Converting (" + employee + ") into (" + transformedEmployee + ")");

        return transformedEmployee;
    }

}