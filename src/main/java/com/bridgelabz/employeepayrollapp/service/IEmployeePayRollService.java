package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.entity.Employee;

import java.util.List;

/**
 * Purpose : Create interface for employee payroll application
 *
 * @author : DAMINI CHANDRAKAR
 * @version : 0.0.1-SNAPSHOT
 * @since : 15-12-2021
 */
public interface IEmployeePayRollService {

    List<EmployeeDto> employeeList();

    Employee getEmployeeById(int id);

    String addEmployee(EmployeeDto employeeDto);

    String updateEmployee(int id, EmployeeDto employeeDto);

    String deleteEmployee(int id);
}
