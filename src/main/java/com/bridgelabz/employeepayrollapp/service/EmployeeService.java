package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.entity.EmployeeEntity;
import com.bridgelabz.employeepayrollapp.exception.EmployeeCustomException;
import com.bridgelabz.employeepayrollapp.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<EmployeeDto> employees() {
        return employeeRepository.findAll().stream()
                .map(atmEntity -> modelMapper.map(atmEntity, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeEntity getEmployeeById(int id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeCustomException(
                        "Invalid ATM Id -> " + id));
        return employeeEntity;
    }

    public EmployeeEntity addEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        return employeeRepository.save(employeeEntity);
    }

    public EmployeeEntity updateEmployee(int id, EmployeeDto employeeDTO) {
        EmployeeEntity employeeEntity = getEmployeeById(id);
        modelMapper.map(employeeDTO, employeeEntity);
         return employeeRepository.save(employeeEntity);
    }

    public String  deleteEmployee(int id) {
        EmployeeEntity atmEntity = getEmployeeById(id);
          employeeRepository.delete(atmEntity);
        return "deleted";
    }

}
