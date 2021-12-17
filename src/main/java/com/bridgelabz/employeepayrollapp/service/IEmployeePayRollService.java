package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.entity.EmployeeEntity;

import java.util.List;

public interface IEmployeePayRollService {

    List<EmployeeDto> employeeList();

    EmployeeEntity getEmployeeById(int id);

    String addEmployee(EmployeeDto employeeDto);

    String updateEmployee(int id, EmployeeDto employeeDto);

    String deleteEmployee(int id);
}
