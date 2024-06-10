package com.cineverse.erpc.account.note.dto;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.employee.aggregate.Employee;
import lombok.Data;

@Data
public class ResponseAccountNoteRegistDTO {
    private long accountNoteId;
    private String accountNote;
    private String accountNoteDate;
    private String accountDeleteDate;
    private Employee employee;
    private Account account;
}
