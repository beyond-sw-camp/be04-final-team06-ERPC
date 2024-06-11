package com.cineverse.erpc.admin.delete.dto.quotation;

import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import lombok.Data;

@Data
public class ResponseQuotationDeleteRequestList {
    private long quotationDeleteRequestId;
    private String quotationDeleteRequestReason;
    private String quotationDeleteRequestStatus;
    private Quotation quotation;
}
