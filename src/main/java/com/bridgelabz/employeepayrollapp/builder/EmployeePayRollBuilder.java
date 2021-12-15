package com.bridgelabz.employeepayrollapp.builder;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.entity.EmployeeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Purpose : This is builder class which holds all the building related application
 *
 * @author : DAMINI CHANDRAKAR
 * @version : 0.0.1-SNAPSHOT
 * @since : 15-12-2021
 */
@Component
public class EmployeePayRollBuilder {
    private ModelMapper modelMapper = new ModelMapper();

    public EmployeeEntity buildEmployeeEntity(EmployeeDto employeeDto, EmployeeEntity employeeEntity) {
        modelMapper.map(employeeDto, employeeEntity);
        return employeeEntity;
    }
}


