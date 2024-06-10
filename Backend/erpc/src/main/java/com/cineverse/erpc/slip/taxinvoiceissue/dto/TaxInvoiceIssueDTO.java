package com.cineverse.erpc.slip.taxinvoiceissue.dto;

import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceRequestStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class TaxInvoiceIssueDTO {
    private long taxInvoiceIssueId;
    private String taxInvoiceRejectReason;
    private String taxInvoiceIssueDate;
    private TaxInvoiceRequestStatus taxInvoiceRequestStatus;
    private long taxInvoiceRequest;
}
