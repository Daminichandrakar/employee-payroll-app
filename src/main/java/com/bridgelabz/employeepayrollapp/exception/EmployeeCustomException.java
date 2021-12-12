package com.bridgelabz.employeepayrollapp.exception;

/**
 * Purpose : Create a custom exception that extends RuntimeExtension
 *
 * @author : DAMINI CHANDRAKAR
 * @since : 03-12-2021
 */
public class EmployeeCustomException extends RuntimeException {
    public EmployeeCustomException(String message) {
        super(message);
    }
}
