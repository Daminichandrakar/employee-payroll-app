package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeePayrollControllerTest {

    @InjectMocks
    private EmployeePayRollController employeePayRollController;

    @Mock
    private EmployeeService employeeService;

    @Test
    void given2EmployeeDto_whenCalledGetAllMethod_shouldReturnListOfEmployeeDto() {
        List<EmployeeDto> employeeList = new ArrayList<>();
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("F");
        employeeDto.setDepartment("It");
        employeeDto.setSalary("15000");
        employeeDto.setStartDate(new Date());
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");
        employeeList.add(employeeDto);

        EmployeeDto employeeDto1 = new EmployeeDto();
        employeeDto1.setName("Siva");
        employeeDto1.setGender("M");
        employeeDto1.setDepartment("It");
        employeeDto1.setSalary("15000");
        employeeDto1.setStartDate(new Date());
        employeeDto1.setImagePath("b.jpg");
        employeeDto1.setNotes("Welcome to it department");
        employeeList.add(employeeDto1);

        when(employeeService.employees()).thenReturn(employeeList);
        List<EmployeeDto> actualResponse = employeePayRollController.getAll();
        for (int i = 0; i < actualResponse.size(); i++) {
            assertEquals(employeeList.get(i).getName(), actualResponse.get(i).getName());
            assertEquals(employeeList.get(i).getDepartment(), actualResponse.get(i).getDepartment());
            assertEquals(employeeList.get(i).getGender(), actualResponse.get(i).getGender());
            assertEquals(employeeList.get(i).getNotes(), actualResponse.get(i).getNotes());
            assertEquals(employeeList.get(i).getSalary(), actualResponse.get(i).getSalary());
            assertEquals(employeeList.get(i).getImagePath(), actualResponse.get(i).getImagePath());
        }
    }

   @Test
    void givenEmployeeDto_whenCalledAddEmployeeMethod_shouldReturnSuccessMessage() {
        String successString = "Atm Added Successfully";
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("F");
        employeeDto.setDepartment("It");
        employeeDto.setSalary("15000");
        employeeDto.setStartDate(new Date());
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");
        when(employeeService.addEmployee(employeeDto)).thenReturn(successString);
        String actualResponseString = employeePayRollController.addEmployee(employeeDto);
        assertEquals(successString, actualResponseString);
    }

    @Test
    void givenEmployeeDto_whenCalledUpdateEmployeeMethod_shouldReturnSuccessMessage() {
        String successString = "Atm Update Successfully";
        int id = 1;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("F");
        employeeDto.setDepartment("It");
        employeeDto.setSalary("15000");
        employeeDto.setStartDate(new Date());
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");
        when(employeeService.updateEmployee(id ,employeeDto)).thenReturn(successString);
        String actualResponseString = employeePayRollController.update(1,employeeDto);
        assertEquals(successString, actualResponseString);
    }

}
