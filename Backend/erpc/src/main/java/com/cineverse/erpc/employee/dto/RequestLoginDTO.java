package com.cineverse.erpc.employee.dto;

import lombok.Data;

@Data
public class RequestLoginDTO {
    private String employeeCode;
    private String employeePassword;
}
