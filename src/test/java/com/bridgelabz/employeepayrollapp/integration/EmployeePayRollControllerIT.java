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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Purpose : To invoke integration test cases for Employee payroll controller class.
 *
 * @author : DAMINI CHANDRAKAR
 * @version : 0.0.1-SNAPSHOT
 * @since : 15-12-2021
 */
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
                        .content("{\"name\": \"Damini\",\"salary\":15000,\"gender\": \"male\"," +
                                "\"startDate\": \"2011-01-02\",\"departments\":[\"It\",\"Cse\"]," +
                                "\"notes\": \"department\",\"imagePath\": \"a.jpg\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void throwsExceptionWhenPassedAnIllegalValueToAdd() throws Exception {
        when(employeePayRollService.addEmployee(any())).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/addressbook/add")
                        .content("{\"name\": \"damini\",\"address\": \"Mahasamund\",\"city\": \"Raipur\"," +
                                "\"state\": \"Chhattishgarh\",\"phoneNumber\": \"91 1234567890\",\"zip\": \"123456\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateEmployeeTest() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("Female");
        employeeDto.setSalary(15000);
        employeeDto.setDepartments(List.of("It"));
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");
        int id = 1;
        when(employeePayRollService.updateEmployee(id,employeeDto)).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/employee/update/1")
                        .content("{\"name\": \"Damini\",\"salary\":15000,\"gender\": \"male\"," +
                                "\"startDate\": \"2011-01-02\",\"departments\":[\"It\",\"Cse\"]," +
                                "\"notes\": \"department\",\"imagePath\": \"a.jpg\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void throwsExceptionWhenPassedAnIllegalValueToUpdateAddressBook() throws Exception {
        int id = 1;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Damini");
        employeeDto.setGender("F");
        employeeDto.setDepartments(List.of("It"));
        employeeDto.setSalary(15000);
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");
        when(employeePayRollService.updateEmployee(id, employeeDto)).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/addressbook/update/1")
                        .content("{\"name\": \"damini\",\"salary\":15000,\"gender\": \"male\"," +
                                "\"startDate\": \"2011-01-02\",\"departments\":[\"It\",\"Cse\"]," +
                                "\"notes\": \"department\",\"imagePath\": \"a.jpg\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteEmployeeTest() throws Exception {
        when(employeePayRollService.deleteEmployee(1)).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/employee/delete/1")
                        .content("{\"name\": \"Damini\",\"salary\":15000,\"gender\": \"male\"," +
                                "\"startDate\": \"2011-01-02\",\"departments\":[\"It\",\"Cse\"]," +
                                "\"notes\": \"department\",\"imagePath\": \"a.jpg\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}
