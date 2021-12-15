package com.bridgelabz.employeepayrollapp.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "employee_payroll")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    private String name;
    private int salary;
    private String gender;
    private Date startDate;
    private String department;
    private String notes;
    private String imagePath;
}

