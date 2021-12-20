package com.bridgelabz.employeepayrollapp.builder;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void givenEmployeeDto_whenNeedToCovertEmployeeDtoToemployee_shouldReturnemployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("F");
        employeeDto.setSalary(15000);
        employeeDto.setDepartments(List.of("It"));
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");
        employeeDto.setStartDate(new Date());

        Employee employee = new Employee();
        employee.setName("Damini");
        employee.setGender("F");
        employee.setDepartments(List.of("It"));
        employee.setSalary(15000);
        employee.setImagePath("a.jpg");
        employee.setNotes("Welcome to it department");
        employee.setStartDate(new Date());
        Employee employee1 = employeePayRollBuilder.buildemployee(employeeDto, employee);
        assertEquals(employeeDto.getName(), employee1.getName());
        assertEquals(employeeDto.getGender(), employee1.getGender());
        assertEquals(employeeDto.getDepartments(), employee1.getDepartments());
        assertEquals(employeeDto.getImagePath(), employee1.getImagePath());
        assertEquals(employeeDto.getSalary(), employee1.getSalary());
        assertEquals(employeeDto.getNotes(), employee1.getNotes());
        assertEquals(employeeDto.getStartDate(), employee1.getStartDate());
    }
}
