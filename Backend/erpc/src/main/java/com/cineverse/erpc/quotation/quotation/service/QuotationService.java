package com.cineverse.erpc.quotation.quotation.service;

import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import com.cineverse.erpc.quotation.quotation.dto.*;

import java.util.List;

public interface QuotationService {
    void registQuotation(RequestRegistQuotationDTO quotation);

    Quotation findQuotationById(long quotationId);

    List<QuotationDTO> findAllQuotations();

    ResponseModifyQuotationDTO modifyQuotation(long quotationId, RequestModifyQuotationDTO quotation);

    ResponseDeleteQuotation deleteQuotation(RequestDeleteQuotation requestDeleteQuotation);
}
