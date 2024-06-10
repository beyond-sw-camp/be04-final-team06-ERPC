package com.cineverse.erpc.employee.dto;

import com.cineverse.erpc.employee.aggregate.EmployeeRank;
import com.cineverse.erpc.employee.aggregate.TeamCode;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class EmployeeDTO {
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
    private String employeeUUID;
}
