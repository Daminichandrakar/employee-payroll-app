package com.bridgelabz.employeepayrollapp.exception;

/**
 * Purpose : To invoke the custom exception
 *
 * @author : DAMINI CHANDRAKAR
 * @version : 0.0.1-SNAPSHOT
 * @since : 15-12-2021
 */
public class EmployeeCustomException extends RuntimeException {
    public EmployeeCustomException(String message) {
        super(message);
    }
}
