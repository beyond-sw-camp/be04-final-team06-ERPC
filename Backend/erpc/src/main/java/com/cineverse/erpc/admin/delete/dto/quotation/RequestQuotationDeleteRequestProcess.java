package com.cineverse.erpc.admin.delete.dto.quotation;

import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import lombok.Data;

@Data
public class RequestQuotationDeleteRequestProcess {
    private long quotationDeleteRequestId;
    private Quotation quotation;
}
