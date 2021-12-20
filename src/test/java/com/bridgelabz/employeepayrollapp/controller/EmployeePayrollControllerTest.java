package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.service.EmployeePayRollService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Purpose : To invoke test cases for Employee payroll controller class.
 *
 * @author : DAMINI CHANDRAKAR
 * @version : 0.0.1-SNAPSHOT
 * @since : 15-12-2021
 */
@ExtendWith(MockitoExtension.class)
public class EmployeePayrollControllerTest {

    @InjectMocks
    private EmployeePayRollController employeePayRollController;
    @Mock
    private EmployeePayRollService employeePayRollService;

    @Test
    void given2EmployeeDto_whenCalledGetAllMethod_shouldReturnListOfEmployeeDto() {
        List<EmployeeDto> employeeList = new ArrayList<>();
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("F");
        employeeDto.setDepartments(List.of("It"));
        employeeDto.setSalary(15000);
        employeeDto.setStartDate(new Date());
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");
        employeeList.add(employeeDto);

        EmployeeDto employeeDto1 = new EmployeeDto();
        employeeDto1.setName("Siva");
        employeeDto1.setGender("M");
        employeeDto1.setDepartments(List.of("It"));
        employeeDto1.setSalary(15000);
        employeeDto1.setStartDate(new Date());
        employeeDto1.setImagePath("b.jpg");
        employeeDto1.setNotes("Welcome to it department");
        employeeList.add(employeeDto1);

        when(employeePayRollService.employeeList()).thenReturn(employeeList);
        ResponseEntity<List<EmployeeDto>> actualResponse = employeePayRollController.getAll();
        assertEquals(employeeList.size(), actualResponse.getBody().size());
        for (int i = 0; i < actualResponse.getBody().size(); i++) {
            assertEquals(employeeList.get(i).getName(), actualResponse.getBody().get(i).getName());
            assertEquals(employeeList.get(i).getDepartments(), actualResponse.getBody().get(i).getDepartments());
            assertEquals(employeeList.get(i).getGender(), actualResponse.getBody().get(i).getGender());
            assertEquals(employeeList.get(i).getNotes(), actualResponse.getBody().get(i).getNotes());
            assertEquals(employeeList.get(i).getSalary(), actualResponse.getBody().get(i).getSalary());
            assertEquals(employeeList.get(i).getImagePath(), actualResponse.getBody().get(i).getImagePath());
        }
    }

    @Test
    void givenEmployeeDto_whenCalledAddEmployeeMethod_shouldReturnSuccessMessage() {
        String successString = "Employee Added Successfully";
        ResponseEntity<String> expectedResponseEntity = new ResponseEntity<>(successString, HttpStatus.OK);
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("F");
        employeeDto.setDepartments(List.of("It"));
        employeeDto.setSalary(15000);
        employeeDto.setStartDate(new Date());
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");
        when(employeePayRollService.addEmployee(employeeDto)).thenReturn(successString);
        ResponseEntity<String> actualResponseString = employeePayRollController.add(employeeDto);
        assertEquals(expectedResponseEntity, actualResponseString);
    }

    @Test
    void givenEmployeeDto_whenCalledUpdateEmployeeMethod_shouldReturnSuccessMessage() {
        String successString = "Employee Update Successfully";
        ResponseEntity<String> expectedResponseEntity = new ResponseEntity<>(successString, HttpStatus.OK);
        int id = 1;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("F");
        employeeDto.setDepartments(List.of("It"));
        employeeDto.setSalary(15000);
        employeeDto.setStartDate(new Date());
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");
        when(employeePayRollService.updateEmployee(id, employeeDto)).thenReturn(successString);
        ResponseEntity<String> actualResponseString = employeePayRollController.update(1, employeeDto);
        assertEquals(expectedResponseEntity, actualResponseString);
    }

    @Test
    void givenId_whenCalledDeleteEmployeeMethod_shouldReturnSuccessMessage() {
        String successString = "Employee Delete Successfully";
        ResponseEntity<String> expectedResponseEntity = new ResponseEntity<>(successString, HttpStatus.OK);
        int id = 1;
        when(employeePayRollService.deleteEmployee(id)).thenReturn(successString);
        ResponseEntity<String> actualResponseString = employeePayRollController.delete(id);
        assertEquals(expectedResponseEntity, actualResponseString);
    }
}
