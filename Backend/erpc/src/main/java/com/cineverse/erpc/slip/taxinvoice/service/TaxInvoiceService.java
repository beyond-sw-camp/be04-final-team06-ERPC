package com.cineverse.erpc.slip.taxinvoice.service;

import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceRequest;
import com.cineverse.erpc.slip.taxinvoice.dto.TaxInvoiceRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TaxInvoiceService {

    TaxInvoiceRequest registTaxInvoiceRequest(TaxInvoiceRequestDTO newTaxInvoice, MultipartFile[] files);

    List<TaxInvoiceRequest> findTaxInvoiceList();

    TaxInvoiceRequestDTO findTaxInvoiceById(long taxInvoiceRequestId);
}
