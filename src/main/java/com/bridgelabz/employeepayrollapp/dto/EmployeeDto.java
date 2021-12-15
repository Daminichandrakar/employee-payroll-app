package com.bridgelabz.employeepayrollapp.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

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
    @Pattern(regexp = "^(?:m|M|male|Male|f|F|female|Female|o|O|Other|other)$", message = "Please type gender F - female " +
            "M - male , O - others/Transgender")
    private String gender;

    @NotNull
    private Date startDate;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{2,}$", message = "Department should be start with capital latter " +
            "& should contain more then 2 character ")
    private String department;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{1,}$", message = "Notes should be start with capital latter " +
            "& should contain more then 3 character ")
    private String notes;

    @NotNull
    private String imagePath;
}
