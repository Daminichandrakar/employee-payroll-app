package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.entity.EmployeeEntity;
import com.bridgelabz.employeepayrollapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/employee")
public class EmployeePayRollController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/get-all")
    public List<EmployeeDto> getAll() {
        return employeeService.employees();
    }

    @PostMapping(value = "/add")
    public String addEmployee(@Valid @RequestBody EmployeeDto employeeDTO) {
        return employeeService.addEmployee(employeeDTO);
    }

    @PutMapping(value = "/update/{id}")
    public EmployeeEntity update(@PathVariable(value = "id") int id, @Valid @RequestBody EmployeeDto atmDto) {
        return employeeService.updateEmployee(id, atmDto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        return employeeService.deleteEmployee(id);
    }
}
