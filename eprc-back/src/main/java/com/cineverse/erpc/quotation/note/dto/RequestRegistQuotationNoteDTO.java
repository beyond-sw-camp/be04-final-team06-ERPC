package com.cineverse.erpc.quotation.note.dto;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestRegistQuotationNoteDTO {
    private String quotationNote;
    private Employee employee;
    private Quotation quotation;
}
