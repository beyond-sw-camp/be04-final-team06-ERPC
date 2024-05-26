package com.cineverse.erpc.quotation.quotation.dto;

import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestDeleteQuotation {
    private String quotationDeleteRequestReason;
    private Quotation quotation;
}
