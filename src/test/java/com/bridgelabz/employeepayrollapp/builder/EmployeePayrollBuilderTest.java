package com.bridgelabz.employeepayrollapp.builder;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.entity.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Purpose : To invoke test cases for Employee payroll builder class.
 *
 * @author : DAMINI CHANDRAKAR
 * @version : 0.0.1-SNAPSHOT
 * @since : 15-12-2021
 */
@ExtendWith(MockitoExtension.class)
public class EmployeePayrollBuilderTest {

    @InjectMocks
    private EmployeePayRollBuilder employeePayRollBuilder;
    @Mock
    private ModelMapper modelMapper;

    @Test
    void givenEmployeeDto_whenNeedToCovertEmployeeDtoToEmployeeEntity_shouldReturnEmployeeEntity() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("F");
        employeeDto.setSalary(15000);
        employeeDto.setDepartments(List.of("It"));
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");
        employeeDto.setStartDate(new Date());

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("Damini");
        employeeEntity.setGender("F");
        employeeEntity.setDepartments(List.of("It"));
        employeeEntity.setSalary(15000);
        employeeEntity.setImagePath("a.jpg");
        employeeEntity.setNotes("Welcome to it department");
        employeeEntity.setStartDate(new Date());
        EmployeeEntity employeeEntity1 = employeePayRollBuilder.buildEmployeeEntity(employeeDto, employeeEntity);
        assertEquals(employeeDto.getName(), employeeEntity1.getName());
        assertEquals(employeeDto.getGender(), employeeEntity1.getGender());
        assertEquals(employeeDto.getDepartments(), employeeEntity1.getDepartments());
        assertEquals(employeeDto.getImagePath(), employeeEntity1.getImagePath());
        assertEquals(employeeDto.getSalary(), employeeEntity1.getSalary());
        assertEquals(employeeDto.getNotes(), employeeEntity1.getNotes());
        assertEquals(employeeDto.getStartDate(), employeeEntity1.getStartDate());
    }
}
