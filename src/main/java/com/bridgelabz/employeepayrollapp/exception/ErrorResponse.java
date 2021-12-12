package com.bridgelabz.employeepayrollapp.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorResponse {
    private int statusCode;
    private Date timestamp;
    private String errors;
}
