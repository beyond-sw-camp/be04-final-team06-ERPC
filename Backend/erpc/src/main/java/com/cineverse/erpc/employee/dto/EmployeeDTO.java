package com.cineverse.erpc.employee.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDTO {
    private Long employeeId;
    private String employeeCode;
    private String employeeName;
    private String employeePassword;
    private String employeeEmail;
    private String employeeHp;
    private String employeeNumber;
    private String employmentDate;
    private String resignationDate;
    private int employeeRankId;
    private int teamCodeId;
    private String employeeUUID;
}
