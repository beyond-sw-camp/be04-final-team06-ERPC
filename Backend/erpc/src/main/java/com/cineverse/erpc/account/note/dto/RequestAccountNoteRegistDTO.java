package com.cineverse.erpc.account.note.dto;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.employee.aggregate.Employee;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestAccountNoteRegistDTO {
    private String accountNote;
    private Account account;
    private Employee employee;
}
