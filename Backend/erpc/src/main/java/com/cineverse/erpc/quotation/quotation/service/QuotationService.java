package com.cineverse.erpc.quotation.quotation.service;

import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import com.cineverse.erpc.quotation.quotation.dto.RequestRegistQuotationDTO;
import com.cineverse.erpc.quotation.quotation.dto.ResponseFindQuotationDTO;

public interface QuotationService {
    void registQuotation(RequestRegistQuotationDTO quotation);

    Quotation findQuotationById(long quotationId);
}
