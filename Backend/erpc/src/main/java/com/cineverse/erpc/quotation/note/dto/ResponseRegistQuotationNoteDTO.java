package com.cineverse.erpc.quotation.note.dto;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import lombok.Data;

@Data
public class ResponseRegistQuotationNoteDTO {
    private long quotationNoteId;
    private String quotationNote;
    private String quotationNoteDate;
    private String quotationDeleteDate;
    private Employee employee;
    private Quotation quotation;
}
