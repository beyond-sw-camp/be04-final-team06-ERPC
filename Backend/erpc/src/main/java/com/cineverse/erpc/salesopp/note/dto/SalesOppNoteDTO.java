package com.cineverse.erpc.salesopp.note.dto;

import com.cineverse.erpc.employee.aggregate.Employee;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SalesOppNoteDTO {
    private long salesOppNoteId;
    private String salesOppNote;
    private String salesOppNoteDate;
    private String salesOppNoteDeleteDate;
    private int salesOppId;
    private Employee employee;
}
