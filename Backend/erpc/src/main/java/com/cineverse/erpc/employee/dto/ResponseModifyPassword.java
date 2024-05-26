package com.cineverse.erpc.employee.dto;

import lombok.Data;

@Data
public class ResponseModifyPassword {
    private long employeeId;
    private String employeePassword;
}
