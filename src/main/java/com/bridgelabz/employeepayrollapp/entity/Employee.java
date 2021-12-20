package com.bridgelabz.employeepayrollapp.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Purpose : To invoke the entity class in employee payroll application
 *
 * @author : DAMINI CHANDRAKAR
 * @version : 0.0.1-SNAPSHOT
 * @since : 15-12-2021
 */
@Data
@Entity
@Table(name = "employee_payroll")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;
    private String name;
    private double salary;
    private String gender;
    private Date startDate;
    @ElementCollection
    @CollectionTable(name = "employee_department", joinColumns = @JoinColumn(name = "id"))
    private List<String> departments;
    private String notes;
    private String imagePath;
}

