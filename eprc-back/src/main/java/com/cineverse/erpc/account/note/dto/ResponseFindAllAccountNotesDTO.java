package com.cineverse.erpc.account.note.dto;

import com.cineverse.erpc.employee.aggregate.Employee;
import lombok.Data;

@Data
public class ResponseFindAllAccountNotesDTO {
    private long accountNoteId;
    private String accountNote;
    private String accountNoteDate;
    private String accountDeleteDate;
    private Employee employee;
}
