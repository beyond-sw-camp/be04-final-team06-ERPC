package com.cineverse.erpc.account.account.dto;

import com.cineverse.erpc.account.account.aggregate.AccountStatus;
import com.cineverse.erpc.employee.aggregate.Employee;
import lombok.Data;

@Data
public class ResponseRegistAccountDTO {
    private long accountId;
    private String accountName;
    private String corporationNum;
    private String accountDeleteDate;
    private String accountRepresentative;   // 대표
    private String corporationStatus;       // 법인여부
    private String accountLocation;
    private String accountContact;          // 전화번호
    private String accountEmail;
    private String accountNote;             // 비고
    private String accountType;             // 업종
    private String accountCode;
    private Employee employee;
    private AccountStatus accountStatus;
}
