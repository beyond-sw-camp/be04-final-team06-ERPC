package com.cineverse.erpc.admin.delete.dto.quotation;

import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestQuotationDeleteRequestProcess {
    private long quotationDeleteRequestId;
    private Quotation quotation;
}
