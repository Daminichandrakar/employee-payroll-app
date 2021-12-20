package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.service.EmployeePayRollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Purpose : To demonstrate different HTTP methods for employee payroll application
 *
 * @author : DAMINI CHANDRAKAR
 * @version : 0.0.1-SNAPSHOT
 * @since : 15-12-2021
 */
@RestController
@RequestMapping(value = "/employee")
public class EmployeePayRollController {

    @Autowired
    private EmployeePayRollService employeePayRollService;

    /**
     * Purpose : To get list of all employee's from database.
     *
     * @return list of all employee's.
     */
    @GetMapping(value = "/get-all")
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return new ResponseEntity<>(employeePayRollService.employeeList(), HttpStatus.OK);
    }

    /**
     * Purpose : To add employee into database.
     *
     * @param employeeDto : input data to be added into database to add employee.
     * @return String : success message "Employee Added Successfully".
     */
    @PostMapping(value = "/add")
    public ResponseEntity<String> add(@Valid @RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<String>(employeePayRollService.addEmployee(employeeDto),HttpStatus.OK);
    }

    /**
     * Purpose : To update employee into database.
     *
     * @param id          : given id which data we want to update in database
     * @param employeeDto : input data to be updated into database.
     * @return String : success message "Employee Updated Successfully".
     */
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> update(@PathVariable(value = "id") int id, @Valid @RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<String>(employeePayRollService.updateEmployee(id, employeeDto),HttpStatus.OK);
    }

    /**
     * Purpose : To delete employee into database.
     *
     * @param id : data will be deleted found by with this given id
     * @return String : success message "Employee Deleted Successfully".
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return new ResponseEntity<String>(employeePayRollService.deleteEmployee(id),HttpStatus.OK);
    }
}
