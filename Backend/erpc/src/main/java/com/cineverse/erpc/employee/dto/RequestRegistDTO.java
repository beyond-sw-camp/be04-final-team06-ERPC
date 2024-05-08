package com.cineverse.erpc.employee.dto;

import lombok.Data;

@Data
public class RequestRegistDTO {
    private String employeeCode;
    private String employeePassword;
    private String employeeEmail;
    private String employeeHp;
    private String employeeNumber;
    private String employeeDate;
    private int employeeRankId;
    private int teamCodeId;
}
