package com.cineverse.erpc.quotation.quotation.service;

import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import com.cineverse.erpc.quotation.quotation.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QuotationService {
    Quotation registQuotation(RequestRegistQuotationDTO quotation, MultipartFile[] files);

    Quotation findQuotationById(long quotationId);

    List<QuotationDTO> findAllQuotations();

    ResponseModifyQuotationDTO modifyQuotation(RequestModifyQuotationDTO quotation, MultipartFile[] files);

    ResponseDeleteQuotation deleteQuotation(RequestDeleteQuotation requestDeleteQuotation);

    ResponseQuotationByCode findQuotationByCode(String quotationCode);

}
