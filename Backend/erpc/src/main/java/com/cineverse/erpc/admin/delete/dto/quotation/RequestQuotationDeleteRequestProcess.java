package com.cineverse.erpc.admin.delete.dto.quotation;

import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestQuotationDeleteRequestProcess {
    private long quotationDeleteRequestId;
    private Quotation quotation;
}
