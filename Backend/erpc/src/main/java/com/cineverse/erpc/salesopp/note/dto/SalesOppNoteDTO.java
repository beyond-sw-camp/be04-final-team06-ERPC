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
    private int salesOppNoteId;
    private String salesOppNote;
    private String salesOppNoteDate;
    private String salesOppNoteDeleteDate;
    private Employee employee;
}
