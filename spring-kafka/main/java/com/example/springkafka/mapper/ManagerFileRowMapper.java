package com.example.springkafka.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import com.example.springkafka.model.*;

public class ManagerFileRowMapper implements FieldSetMapper<Employee> {

    @Override
    public Employee mapFieldSet(FieldSet fieldSet) {
        Employee manager = new Employee();
        manager.setEmployeeId(fieldSet.readString("managerId"));
        manager.setFirstName(fieldSet.readString("firstName"));
        manager.setLastName(fieldSet.readString("lastName"));
        manager.setEmail(fieldSet.readString("email"));
        try {
            manager.setAge(fieldSet.readInt("dept"));
        } catch (Exception e) {

        }
        return manager;
    }

}

