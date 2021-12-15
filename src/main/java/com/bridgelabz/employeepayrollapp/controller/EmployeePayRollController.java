package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.service.EmployeePayRollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Purpose : This is builder class which holds all the building related application
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

    @GetMapping(value = "/get-all")
    public List<EmployeeDto> getAll() {
        return employeePayRollService.employeeList();
    }

    @PostMapping(value = "/add")
    public String add(@Valid @RequestBody EmployeeDto employeeDTO) {
        return employeePayRollService.addEmployee(employeeDTO);
    }

    @PutMapping(value = "/update/{id}")
    public String update(@PathVariable(value = "id") int id, @Valid @RequestBody EmployeeDto employeeDto) {
        return employeePayRollService.updateEmployee(id, employeeDto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        return employeePayRollService.deleteEmployee(id);
    }
}
