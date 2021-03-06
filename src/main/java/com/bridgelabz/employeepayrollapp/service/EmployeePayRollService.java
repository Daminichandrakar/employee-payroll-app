package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.builder.EmployeePayRollBuilder;
import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.entity.Employee;
import com.bridgelabz.employeepayrollapp.exception.EmployeeCustomException;
import com.bridgelabz.employeepayrollapp.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Purpose : To demonstrate business logic for Employee Payroll Application
 *
 * @author : DAMINI CHANDRAKAR
 * @version : 0.0.1-SNAPSHOT
 * @since : 15-12-2021
 */
@Service
public class EmployeePayRollService implements IEmployeePayRollService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmployeePayRollBuilder employeePayRollBuilder;

    /**
     * Purpose : This method is used to get back the list of employee details
     *
     * @return the list of all employee
     */
    @Override
    public List<EmployeeDto> employeeList() {
        return employeeRepository.findAll().stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Purpose : This method is used to get the employee details by respective employee id
     *
     * @param id : takes the employee id of that particular employee entity
     * @return the employee entity using the employee id
     */
    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeCustomException(
                        "Invalid Employee Id -> " + id));
        return employee;
    }

    /**
     * Purpose : This method is used to add the employee details by using of
     * employeeDto.
     *
     * @param employeeDto : takes the employee details as DTO to provide the
     *                    repository for storing in database
     * @return String : Success message for adding data into database.
     */
    @Override
    public String addEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employeeRepository.save(employee);
        return "Employee Added Successfully";
    }

    /**
     * Purpose : This method is used to update the employee details by using their
     * respective employee id
     *
     * @param id          : takes the employee id for updating that particular employee.
     * @param employeeDto : takes the updated employee details as DTO
     *                    and update in database
     * @return String : Success message for updating data into database.
     */
    @Override
    public String updateEmployee(int id, EmployeeDto employeeDto) {
        Employee employee = getEmployeeById(id);
        employeePayRollBuilder.buildemployee(employeeDto, employee);
        employeeRepository.save(employee);
        return "Employee Updated Successfully";
    }

    /**
     * Purpose : This method is used to delete the employee details by using the respective employee id
     *
     * @param id : takes the employee id for deleting that particularly employee entity
     * @return String : Success message for deleting data into database.
     */
    @Override
    public String deleteEmployee(int id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
        return "Employee Deleted Successfully";
    }
}
