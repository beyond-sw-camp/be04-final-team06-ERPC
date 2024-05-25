package com.cineverse.erpc.employee.dto;

import com.cineverse.erpc.employee.aggregate.EmployeeRank;
import com.cineverse.erpc.employee.aggregate.TeamCode;
import lombok.Data;

@Data
public class ResponseRegistDTO {
    private long employeeId;
    private String employeeCode;
    private String employeeName;
    private String employeeEmail;
    private String employeeHp;
    private String employeeNumber;
    private String employmentDate;
    private String resignationDate;
    private String employeePassword;
    private EmployeeRank employeeRank;
    private TeamCode teamCode;
    private String employeeUUID;
}
