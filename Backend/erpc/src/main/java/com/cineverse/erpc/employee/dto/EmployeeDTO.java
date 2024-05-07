package com.cineverse.erpc.employee.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDTO {
    private Long employId;
    private int employCode;
    private String employPassword;
    private String employEmail;
    private String employHp;
    private String employNumber;
    private String employDate;
    private String resignationDate;
    private int employRankId;
    private int teamCodeId;
}
