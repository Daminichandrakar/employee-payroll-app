package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.builder.EmployeePayRollBuilder;
import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.entity.Employee;
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
    private ModelMapper modelMapper;
    @Mock
    private EmployeePayRollBuilder employeePayRollBuilder;

    @Test
    void whenGetAllEmployeeMethodIsCalled_ShouldReturnListOfemployeeDto() {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setName("Damini");
        employee.setGender("F");
        employee.setDepartments(List.of("It"));
        employee.setSalary(15000);
        employee.setStartDate(new Date());
        employee.setNotes("Welcome to it department");
        employee.setImagePath("a.jpg");
        employeeList.add(employee);
        Employee employee1 = new Employee();
        employee1.setEmployeeId(2);
        employee1.setName("Damini");
        employee1.setGender("F");
        employee1.setDepartments(List.of("It"));
        employee1.setSalary(15000);
        employee1.setStartDate(new Date());
        employee1.setNotes("Welcome to it department");
        employee1.setImagePath("a.jpg");
        employeeList.add(employee1);

        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("F");
        employeeDto.setDepartments(List.of("It"));
        employeeDto.setSalary(15000);
        employeeDto.setStartDate(new Date());
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");
        employeeDtoList.add(employeeDto);
        EmployeeDto employeeDto2 = new EmployeeDto();
        employeeDto2.setName("Siva");
        employeeDto2.setGender("M");
        employeeDto2.setDepartments(List.of("It"));
        employeeDto2.setSalary(15000);
        employeeDto2.setStartDate(new Date());
        employeeDto2.setNotes("Welcome to Cse department");
        employeeDto2.setImagePath("a.jpg");
        employeeDtoList.add(employeeDto2);

        when(employeeRepository.findAll()).thenReturn(employeeList);
        when(modelMapper.map(employeeList.get(0), EmployeeDto.class)).thenReturn(employeeDto);
        when(modelMapper.map(employeeList.get(1), EmployeeDto.class)).thenReturn(employeeDto2);
        List<EmployeeDto> actualListOfEmployee = employeePayRollService.employeeList();
        assertEquals(2, actualListOfEmployee.size());
        assertEquals(employeeDtoList, actualListOfEmployee);
    }

    @Test
    void whenAddEmployeeCalled_shouldAddEmployeeAndGenerateSuccessMessage() {
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setName("Damini");
        employee.setGender("F");
        employee.setDepartments(List.of("It"));
        employee.setSalary(15000);
        employee.setStartDate(new Date());
        employee.setNotes("Welcome to it department");
        employee.setImagePath("a.jpg");

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("F");
        employeeDto.setDepartments(List.of("It"));
        employeeDto.setSalary(15000);
        employeeDto.setStartDate(new Date());
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");

        when(modelMapper.map(employeeDto, Employee.class)).thenReturn(employee);
        String actualStringMessage = employeePayRollService.addEmployee(employeeDto);
        assertEquals("Employee Added Successfully", actualStringMessage);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void whenUpdateEmployeeMethodIsCalled_ShouldUpdateEmployeeDetailsAndReturnSuccessMessage() {
        int id = 1;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("F");
        employeeDto.setDepartments(List.of("It"));
        employeeDto.setSalary(15000);
        employeeDto.setStartDate(new Date());
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");

        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setName("Damini");
        employee.setGender("F");
        employeeDto.setDepartments(List.of("It"));
        employee.setSalary(15000);
        employee.setStartDate(new Date());
        employee.setNotes("Welcome to it department");
        employee.setImagePath("a.jpg");

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        employee.setName(employeeDto.getName());
        employee.setGender(employeeDto.getGender());
        employee.setDepartments(employeeDto.getDepartments());

        when(employeePayRollBuilder.buildemployee(employeeDto, employee)).
                thenReturn(employee);
        String actualSuccessMessage = employeePayRollService.updateEmployee(id, employeeDto);
        verify(employeeRepository, times(1)).save(employee);
        assertEquals("Employee Updated Successfully", actualSuccessMessage);
        assertEquals(employeeDto.getName(), employee.getName());
    }

    @Test
    void whenUpdateEmployeeMethodIsCalled_IfNotFoundId_shouldThrowExceptionMessage() {
        int id = 1;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("F");
        employeeDto.setDepartments(List.of("It"));
        employeeDto.setSalary(15000);
        employeeDto.setStartDate(new Date());
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");

        when(employeeRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EmployeeCustomException.class, () -> employeePayRollService.
                updateEmployee(id, employeeDto));
    }

    @Test
    void givenDeleteEmployeeMethodIsCalledWithAnId_ShouldDeleteTheDataOfThatId() {
        int id = 1;
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setName("Damini");
        employee.setGender("F");
        employee.setDepartments(List.of("It"));
        employee.setSalary(15000);
        employee.setStartDate(new Date());
        employee.setNotes("Welcome to it department");
        employee.setImagePath("a.jpg");

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        String actualMessage = employeePayRollService.deleteEmployee(id);
        assertEquals("Employee Deleted Successfully", actualMessage);
        verify(employeeRepository, times(1)).delete(employee);
    }

    @Test
    void whenDeleteEmployeeMethodIsCalled_IfIdNotFound_shouldThrowExceptionMessage() {
        int id = 1;
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setName("Damini");
        employee.setGender("F");
        employee.setDepartments(List.of("It"));
        employee.setSalary(15000);
        employee.setStartDate(new Date());
        employee.setNotes("Welcome to it department");
        employee.setImagePath("a.jpg");
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EmployeeCustomException.class, () -> employeePayRollService.deleteEmployee(id));
    }

    @Test
    void givenId_whenGetEmployeeByIdIsCalled_IfIdNotFound_shouldThrowExceptionMessage() {
        int id = 1;
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EmployeeCustomException.class, () -> employeePayRollService.getEmployeeById(id));
    }

    @Test
    void givenId_whenGetEmployeeByIdIsCalled_shouldReturnEmployee() {
        int id = 1;
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setName("Damini");
        employee.setGender("F");
        employee.setDepartments(List.of("It"));
        employee.setSalary(15000);
        employee.setStartDate(new Date());
        employee.setNotes("Welcome to it department");
        employee.setImagePath("a.jpg");
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        Employee actualEntity = employeePayRollService.getEmployeeById(id);
        assertEquals(actualEntity.getGender(), employee.getGender());
        assertEquals(actualEntity.getName(), employee.getName());
        assertEquals(actualEntity.getDepartments(), employee.getDepartments());
    }
}
