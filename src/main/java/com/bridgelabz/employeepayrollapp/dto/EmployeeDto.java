package com.bridgelabz.employeepayrollapp.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * Purpose : To invoke the employee details from client
 *
 * @author : DAMINI CHANDRAKAR
 * @version : 0.0.1-SNAPSHOT
 * @since : 15-12-2021
 */
@Data
public class EmployeeDto {

    @NotNull
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}$", message = "Name should be start with capital latter " +
            "& should contain more then 3 character ")
    private String name;
    @NotNull
    @Min(value = 10000, message = "Minimum wage should be more than 10000")
    private double salary;
    @NotNull
    @Pattern(regexp = "^(male|female|other)$", message = "Please type gender female ," +
            "male ,others")
    private String gender;
    @NotNull
    @CreationTimestamp
    private Date startDate;
    @NotNull
    @Size(max = 50, message = "Department name should be in 50 character")
    private List<String> departments;
    @NotNull
    @Size(max = 150, message = "Notes should be in 150 character")
    private String notes;
    @NotEmpty(message = "Image path can not be empty")
    private String imagePath;
}
