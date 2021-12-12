package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class EmployeePayrollControllerTest {

    @InjectMocks
    private EmployeePayRollController employeePayRollController;

    @Mock
    private EmployeeService employeeService;

    @Test
    void name() {
    }
}
