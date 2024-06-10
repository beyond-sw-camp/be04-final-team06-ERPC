package com.cineverse.erpc.quotation.note.dto;

import com.cineverse.erpc.employee.aggregate.Employee;
import lombok.Data;

@Data
public class ResponseFindAllQuotationNotesDTO {
    private long quotationNoteId;
    private String quotationNote;
    private String quotationNoteDate;
    private String quotationDeleteDate;
    private Employee employee;
}
