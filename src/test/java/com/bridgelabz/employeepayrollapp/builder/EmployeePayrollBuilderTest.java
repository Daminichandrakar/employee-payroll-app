package com.bridgelabz.employeepayrollapp.builder;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.entity.EmployeeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeePayrollBuilderTest {

    @InjectMocks
    private EmployeePayRollBuilder employeePayRollBuilder;
    private ModelMapper modelMapper;

    @Test
    void name() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("Female");
        employeeDto.setSalary(15000);
        employeeDto.setDepartment("It");
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");
        employeeDto.setStartDate(new Date());

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity = employeePayRollBuilder.buildEmployeeEntity(employeeDto,employeeEntity);
        assertEquals(employeeDto.getName(),employeeEntity.getName());

    }
}
