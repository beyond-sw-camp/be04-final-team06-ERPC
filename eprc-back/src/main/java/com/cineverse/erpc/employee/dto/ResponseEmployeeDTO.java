package com.cineverse.erpc.employee.dto;

import com.cineverse.erpc.employee.aggregate.EmployeeRank;
import com.cineverse.erpc.employee.aggregate.TeamCode;
import lombok.Data;

@Data
public class ResponseEmployeeDTO {
    private long employeeId;
    private String employeeCode;
    private String employeeName;
    private String employeePassword;
    private String employeeEmail;
    private String employeeHp;
    private String employeeNumber;
    private String employmentDate;
    private String resignationDate;
    private EmployeeRank employeeRank;
    private TeamCode teamCode;
}
