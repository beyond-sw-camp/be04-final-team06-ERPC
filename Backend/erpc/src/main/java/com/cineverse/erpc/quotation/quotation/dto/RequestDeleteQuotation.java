package com.cineverse.erpc.quotation.quotation.dto;

import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import lombok.Data;

@Data
public class RequestDeleteQuotation {
    private String quotationDeleteRequestReason;
    private Quotation quotation;
}
