package com.bridgelabz.employeepayrollapp.integration;


import com.bridgelabz.employeepayrollapp.controller.EmployeePayRollController;
import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.service.EmployeePayRollService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeePayRollController.class)
public class EmployeePayRollControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeePayRollService employeePayRollService;

    @Test
    void getAllEmployeeTest() throws Exception {
        when(employeePayRollService.employeeList()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/get-all"))
                .andExpect(status().isOk());
    }

    @Test
    void addEmployeeTest() throws Exception {
        when(employeePayRollService.addEmployee(any())).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/employee/add")
                        .content("{\"name\": \"Siva\",\"salary\":10000,\"gender\": \"male\",\"startDate\": \"2011-01-02\",\"department\": \"IT\", \"notes\": \"Department\",\"imagePath\": \"a.jpg\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void updateEmployeeTest() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("Female");
        employeeDto.setSalary(15000);
        employeeDto.setDepartment("It");
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");
        employeeDto.setStartDate(new Date());
        int id = 1;
        when(employeePayRollService.updateEmployee(id,employeeDto)).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/employee/update/1")
                        .content("{\"name\": \"Siva\",\"salary\":10000,\"gender\": \"male\",\"startDate\": \"2011-01-02\",\"department\": \"IT\", \"notes\": \"Department\",\"imagePath\": \"a.jpg\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEmployeeTest() throws Exception {
        when(employeePayRollService.deleteEmployee(1)).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/employee/delete/1")
                        .content("{\"name\": \"Siva\",\"salary\":10000,\"gender\": \"male\",\"startDate\": \"2011-01-02\",\"department\": \"IT\", \"notes\": \"Department\",\"imagePath\": \"a.jpg\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}
