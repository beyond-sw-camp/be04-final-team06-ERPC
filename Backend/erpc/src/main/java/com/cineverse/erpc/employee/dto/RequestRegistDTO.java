package com.cineverse.erpc.employee.dto;

import com.cineverse.erpc.employee.aggregate.EmployeeRank;
import com.cineverse.erpc.employee.aggregate.TeamCode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestRegistDTO {
    private long employeeId;
    private String employeeCode;
    private String employeeName;
    private String employeePassword;
    private String employeeEmail;
    private String employeeHp;
    private String employeeNumber;
    private String employmentDate;
    private EmployeeRank employeeRank;
    private TeamCode teamCode;
}
