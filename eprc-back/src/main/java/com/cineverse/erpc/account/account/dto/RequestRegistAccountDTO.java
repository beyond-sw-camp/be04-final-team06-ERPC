package com.cineverse.erpc.account.account.dto;

import com.cineverse.erpc.account.account.aggregate.AccountStatus;
import com.cineverse.erpc.employee.aggregate.Employee;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestRegistAccountDTO {
    private long accountId;
    private String accountName;
    private String accountDeleteDate;
    private String corporationNum;
    private String accountRepresentative;   // 대표
    private String corporationStatus;       // 법인여부
    private String accountLocation;
    private String accountContact;          // 전화번호
    private String accountEmail;
    private String accountNote;             // 비고
    private String accountType;             // 업종
    private Employee employee;
    private AccountStatus accountStatus;
}
