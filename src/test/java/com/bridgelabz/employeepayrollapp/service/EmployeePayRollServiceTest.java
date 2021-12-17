package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.builder.EmployeePayRollBuilder;
import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.entity.EmployeeEntity;
import com.bridgelabz.employeepayrollapp.exception.EmployeeCustomException;
import com.bridgelabz.employeepayrollapp.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

/**
 * Purpose : To invoke test cases for Employee payroll service class.
 *
 * @author : DAMINI CHANDRAKAR
 * @version : 0.0.1-SNAPSHOT
 * @since : 15-12-2021
 */
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
        List<EmployeeEntity> employeeEntityList = new ArrayList<>();
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmployeeId(1);
        employeeEntity.setName("Damini");
        employeeEntity.setGender("F");
        employeeEntity.setDepartments(List.of("It"));
        employeeEntity.setSalary(15000);
        employeeEntity.setStartDate(new Date());
        employeeEntity.setNotes("Welcome to it department");
        employeeEntity.setImagePath("a.jpg");
        employeeEntityList.add(employeeEntity);
        EmployeeEntity employeeEntity1 = new EmployeeEntity();
        employeeEntity1.setEmployeeId(2);
        employeeEntity1.setName("Damini");
        employeeEntity1.setGender("F");
        employeeEntity1.setDepartments(List.of("It"));
        employeeEntity1.setSalary(15000);
        employeeEntity1.setStartDate(new Date());
        employeeEntity1.setNotes("Welcome to it department");
        employeeEntity1.setImagePath("a.jpg");
        employeeEntityList.add(employeeEntity1);

        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("F");
        employeeDto.setDepartments(List.of("It"));
        employeeDto.setSalary(15000);
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");
        employeeDtoList.add(employeeDto);
        EmployeeDto employeeDto2 = new EmployeeDto();
        employeeDto2.setName("Siva");
        employeeDto2.setGender("M");
        employeeDto2.setDepartments(List.of("It"));
        employeeDto2.setSalary(15000);
        employeeDto2.setNotes("Welcome to Cse department");
        employeeDto2.setImagePath("a.jpg");
        employeeDtoList.add(employeeDto2);

        when(employeeRepository.findAll()).thenReturn(employeeEntityList);
        when(modelMapper.map(employeeEntityList.get(0), EmployeeDto.class)).thenReturn(employeeDto);
        when(modelMapper.map(employeeEntityList.get(1), EmployeeDto.class)).thenReturn(employeeDto2);
        List<EmployeeDto> actualListOfEmployee = employeePayRollService.employeeList();
        assertEquals(2, actualListOfEmployee.size());
        assertEquals(employeeDtoList, actualListOfEmployee);
    }

    @Test
    void whenAddEmployeeCalled_shouldAddEmployeeAndGenerateSuccessMessage() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmployeeId(1);
        employeeEntity.setName("Damini");
        employeeEntity.setGender("F");
        employeeEntity.setDepartments(List.of("It"));
        employeeEntity.setSalary(15000);
        employeeEntity.setStartDate(new Date());
        employeeEntity.setNotes("Welcome to it department");
        employeeEntity.setImagePath("a.jpg");

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("F");
        employeeDto.setDepartments(List.of("It"));
        employeeDto.setSalary(15000);
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");

        when(modelMapper.map(employeeDto, EmployeeEntity.class)).thenReturn(employeeEntity);
        String actualStringMessage = employeePayRollService.addEmployee(employeeDto);
        assertEquals("Employee Added Successfully", actualStringMessage);
        verify(employeeRepository, times(1)).save(employeeEntity);
    }

    @Test
    void whenUpdateEmployeeMethodIsCalled_ShouldUpdateEmployeeDetailsAndReturnSuccessMessage() {
        int id = 1;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("F");
        employeeDto.setDepartments(List.of("It"));
        employeeDto.setSalary(15000);
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmployeeId(1);
        employeeEntity.setName("Damini");
        employeeEntity.setGender("F");
        employeeDto.setDepartments(List.of("It"));
        employeeEntity.setSalary(15000);
        employeeEntity.setStartDate(new Date());
        employeeEntity.setNotes("Welcome to it department");
        employeeEntity.setImagePath("a.jpg");

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employeeEntity));
        employeeEntity.setName(employeeDto.getName());
        employeeEntity.setGender(employeeDto.getGender());
        employeeEntity.setDepartments(employeeDto.getDepartments());

        when(employeePayRollBuilder.buildEmployeeEntity(employeeDto, employeeEntity)).
                thenReturn(employeeEntity);
        String actualSuccessMessage = employeePayRollService.updateEmployee(id, employeeDto);
        verify(employeeRepository, times(1)).save(employeeEntity);
        assertEquals("Employee Updated Successfully", actualSuccessMessage);
        assertEquals(employeeDto.getName(), employeeEntity.getName());
    }

    @Test
    void whenUpdateEmployeeMethodIsCalled_IfNotFoundId_shouldThrowExceptionMessage() {
        int id = 1;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("F");
        employeeDto.setDepartments(List.of("It"));
        employeeDto.setSalary(15000);
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");

        when(employeeRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EmployeeCustomException.class, () -> employeePayRollService.
                updateEmployee(id, employeeDto));
    }

    @Test
    void givenDeleteEmployeeMethodIsCalledWithAnId_ShouldDeleteTheDataOfThatId() {
        int id = 1;
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmployeeId(1);
        employeeEntity.setName("Damini");
        employeeEntity.setGender("F");
        employeeEntity.setDepartments(List.of("It"));
        employeeEntity.setSalary(15000);
        employeeEntity.setStartDate(new Date());
        employeeEntity.setNotes("Welcome to it department");
        employeeEntity.setImagePath("a.jpg");

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employeeEntity));
        String actualMessage = employeePayRollService.deleteEmployee(id);
        assertEquals("Employee Deleted Successfully", actualMessage);
        verify(employeeRepository, times(1)).delete(employeeEntity);
    }

    @Test
    void whenDeleteEmployeeMethodIsCalled_IfIdNotFound_shouldThrowExceptionMessage() {
        int id = 1;
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmployeeId(1);
        employeeEntity.setName("Damini");
        employeeEntity.setGender("F");
        employeeEntity.setDepartments(List.of("It"));
        employeeEntity.setSalary(15000);
        employeeEntity.setStartDate(new Date());
        employeeEntity.setNotes("Welcome to it department");
        employeeEntity.setImagePath("a.jpg");
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EmployeeCustomException.class, () -> employeePayRollService.deleteEmployee(id));
    }
}
