package com.bridgelabz.employeepayrollapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * Purpose : To invoke the error details for exception
 *
 * @author : DAMINI CHANDRAKAR
 * @version : 0.0.1-SNAPSHOT
 * @since : 15-12-2021
 */
@Data
public class ErrorResponse {
    private int statusCode;
    private Date timestamp;
    private String errors;
}
