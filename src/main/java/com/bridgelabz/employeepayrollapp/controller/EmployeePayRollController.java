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

    @GetMapping(value = "/employees")
    public List<EmployeeDto> employees() {
        return employeeService.employees();
    }

    @PostMapping(value = "/add")
    public EmployeeEntity addEmployee( @Valid @RequestBody EmployeeDto employeeDTO) {
        return employeeService.addEmployee(employeeDTO);
    }


    /**
     * Purpose : To update atm card as per id number in atm-system program using http method.
     *
     * @param id     : As per id card details will be updated.
     * @param atmDto : atmDto is used to update card details as per regex pattern.
     * @return : atm service to update atm details.
     */
    @PutMapping(value = "/update/{id}")
    public EmployeeEntity updateAtm(@PathVariable(value = "id") int id, @Valid @RequestBody EmployeeDto atmDto) {
        return employeeService.updateEmployee(id, atmDto);
    }

    /**
     * Purpose : To delete atm card as per id number in atm-system program using http method.
     *
     * @param id : As per id card details will be deleted.
     * @return : atm service to delete atm details.
     */
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        return employeeService.deleteEmployee(id);
    }
}
