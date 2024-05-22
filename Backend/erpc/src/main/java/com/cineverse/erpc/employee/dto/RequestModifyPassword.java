package com.cineverse.erpc.employee.dto;

import lombok.Data;

@Data
public class RequestModifyPassword {
    private long employeeId;
    private String employeePassword;
}
