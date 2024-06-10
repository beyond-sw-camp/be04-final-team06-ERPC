package com.cineverse.erpc.slip.taxinvoice.dto;

import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceRequestStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class TaxInvoiceProcessDTO {
    private long taxInvoiceProcessId;
    private String taxInvoiceProcessContent;
    private String taxInvoiceProcessDate;
    private long taxInvoiceRequestId;
    private TaxInvoiceRequestStatus taxInvoiceRequestStatus;
}
