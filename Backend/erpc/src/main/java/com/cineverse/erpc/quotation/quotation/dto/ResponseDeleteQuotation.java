package com.cineverse.erpc.quotation.quotation.dto;


import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import lombok.Data;

@Data
public class ResponseDeleteQuotation {
    private long quotationDeleteRequestId;
    private String quotationDeleteRequestReason;
    private String quotationDeleteRequestStatus;
    private Quotation quotation;
}
