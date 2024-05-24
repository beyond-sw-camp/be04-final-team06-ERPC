package com.cineverse.erpc.salesopp.note.dto;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOpp;
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
    private SalesOpp salesOpp;
    private Employee employee;
}
