package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.builder.EmployeePayRollBuilder;
import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.entity.EmployeeEntity;
import com.bridgelabz.employeepayrollapp.exception.EmployeeCustomException;
import com.bridgelabz.employeepayrollapp.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeePayRollServiceTest {

    @InjectMocks
    private EmployeePayRollService employeePayRollService;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    ModelMapper modelMapper;
    @Mock
    EmployeePayRollBuilder employeePayRollBuilder;

    @Test
    void whenGetAllEmployeeMethodIsCalled_ShouldReturnListOfEmployeeResponseDto() {
        List<EmployeeEntity> employeeList = new ArrayList<>();
        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmployeeId(1);
        employee.setName("Damini");
        employee.setGender("Female");
        employee.setDepartment("Cse");
        employeeList.add(employee);
        EmployeeEntity employee2 = new EmployeeEntity();
        employee2.setEmployeeId(2);
        employee2.setName("Siva");
        employee2.setGender("Male");
        employee2.setDepartment("It");
        employeeList.add(employee2);

        List<EmployeeDto> employeeResponseList = new ArrayList<>();
        EmployeeDto employeeResponseDto = new EmployeeDto();
        employeeResponseDto.setName("Damini");
        employeeResponseDto.setGender("Male");
        employeeResponseDto.setDepartment("Cse");
        employeeResponseList.add(employeeResponseDto);
        EmployeeDto employeeResponseDto2 = new EmployeeDto();
        employeeResponseDto2.setName("Siva");
        employeeResponseDto2.setGender("Male");
        employeeResponseDto2.setDepartment("It");
        employeeResponseList.add(employeeResponseDto2);

        when(employeeRepository.findAll()).thenReturn(employeeList);
        when(modelMapper.map(employeeList.get(0), EmployeeDto.class)).thenReturn(employeeResponseDto);
        when(modelMapper.map(employeeList.get(1), EmployeeDto.class)).thenReturn(employeeResponseDto2);
        List<EmployeeDto> actualListOfEmployee = employeePayRollService.employeeList();
        assertEquals(2, actualListOfEmployee.size());
        assertEquals(employeeResponseList, actualListOfEmployee);
    }
}
