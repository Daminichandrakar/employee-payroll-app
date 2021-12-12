package com.bridgelabz.employeepayrollapp.exception;

import lombok.Data;

import java.util.Date;

/**
 * Purpose : Class is created for Error-Response details
 *
 * @author : DAMINI CHANDRAKAR
 * @since : 03-12-2021
 */
@Data
public class ErrorResponse {
    private int statusCode;
    private Date timestamp;
    private String errors;
}
