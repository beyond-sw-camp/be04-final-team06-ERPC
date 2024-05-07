package com.cineverse.erpc.employee.dto;

import lombok.Data;

@Data
public class RequestRegistDTO {
    private int employCode;
    private String employPassword;
    private String employEmail;
    private String employHp;
    private String employNumber;
    private String employDate;
    private int employRankId;
    private int teamCodeId;
}
