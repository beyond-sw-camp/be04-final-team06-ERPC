package com.cineverse.erpc.employee.dto;

import lombok.Data;

@Data
public class ResponseRegistDTO {
    private String employeeCode;
    private String employeeName;
    private String employeeEmail;
    private String employeeHp;
    private String employeeNumber;
    private String employmentDate;
    private String resignationDate;
    private int employRankId;
    private int teamCodeId;
    private String employeeUUID;
}
