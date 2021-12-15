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
import java.util.Date;
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
    void whenGetAllEmployeeMethodIsCalled_ShouldReturnListOfemployeeDto() {
        List<EmployeeEntity> employeeList = new ArrayList<>();
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmployeeId(1);
        employeeEntity.setName("Damini");
        employeeEntity.setGender("F");
        employeeEntity.setDepartment("It");
        employeeEntity.setSalary(15000);
        employeeEntity.setStartDate(new Date());
        employeeEntity.setNotes("Welcome to it department");
        employeeEntity.setImagePath("a.jpg");
        employeeList.add(employeeEntity);
        EmployeeEntity employeeEntity1 = new EmployeeEntity();
        employeeEntity1.setEmployeeId(2);
        employeeEntity1.setName("Damini");
        employeeEntity1.setGender("F");
        employeeEntity1.setDepartment("It");
        employeeEntity1.setSalary(15000);
        employeeEntity1.setStartDate(new Date());
        employeeEntity1.setNotes("Welcome to it department");
        employeeEntity1.setImagePath("a.jpg");;
        employeeList.add(employeeEntity1);

        List<EmployeeDto> employeeResponseList = new ArrayList<>();
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("F");
        employeeDto.setDepartment("It");
        employeeDto.setSalary(15000);
        employeeDto.setStartDate(new Date());
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");
        employeeResponseList.add(employeeDto);
        EmployeeDto employeeDto2 = new EmployeeDto();
        employeeDto2.setName("Siva");
        employeeDto2.setGender("M");
        employeeDto2.setDepartment("Cse");
        employeeDto2.setSalary(15000);
        employeeDto2.setStartDate(new Date());
        employeeDto2.setNotes("Welcome to Cse department");
        employeeDto2.setImagePath("a.jpg");
        employeeResponseList.add(employeeDto2);

        when(employeeRepository.findAll()).thenReturn(employeeList);
        when(modelMapper.map(employeeList.get(0), EmployeeDto.class)).thenReturn(employeeDto);
        when(modelMapper.map(employeeList.get(1), EmployeeDto.class)).thenReturn(employeeDto2);
        List<EmployeeDto> actualListOfEmployee = employeePayRollService.employeeList();
        assertEquals(2, actualListOfEmployee.size());
        assertEquals(employeeResponseList, actualListOfEmployee);
    }

    @Test
    void whenAddEmployeeCalled_shouldAddEmployeeAndGenerateSuccessMessage() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmployeeId(1);
        employeeEntity.setName("Damini");
        employeeEntity.setGender("F");
        employeeEntity.setDepartment("It");
        employeeEntity.setSalary(15000);
        employeeEntity.setStartDate(new Date());
        employeeEntity.setNotes("Welcome to it department");
        employeeEntity.setImagePath("a.jpg");

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("F");
        employeeDto.setDepartment("It");
        employeeDto.setSalary(15000);
        employeeDto.setStartDate(new Date());
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");

        when(modelMapper.map(employeeDto, EmployeeEntity.class)).thenReturn(employeeEntity);
        String actualStringMessage = employeePayRollService.addEmployee(employeeDto);
        assertEquals("Employee Added Successfully", actualStringMessage);
        verify(employeeRepository, times(1)).save(employeeEntity);
    }
}
