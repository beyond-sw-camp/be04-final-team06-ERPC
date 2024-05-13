package com.cineverse.erpc.quotation.quotation.service;

import com.cineverse.erpc.quotation.quotation.dto.RequestRegistQuotationDTO;

public interface QuotationService {
    void registQuotation(RequestRegistQuotationDTO quotation);
}
