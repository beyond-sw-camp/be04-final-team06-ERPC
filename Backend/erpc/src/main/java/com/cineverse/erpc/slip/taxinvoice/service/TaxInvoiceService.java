package com.cineverse.erpc.slip.taxinvoice.service;

import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceProcess;
import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceRequest;
import com.cineverse.erpc.slip.taxinvoice.dto.TaxInvoiceProcessDTO;
import com.cineverse.erpc.slip.taxinvoice.dto.TaxInvoiceRequestDTO;

import java.util.List;

public interface TaxInvoiceService {

    TaxInvoiceRequest registTaxInvoiceRequest(TaxInvoiceRequestDTO newTaxInvoice);

    List<TaxInvoiceRequest> findTaxInvoiceList();

    TaxInvoiceRequestDTO findTaxInvoiceById(Long taxInvoiceRequestId);

    TaxInvoiceProcess modifyProcess(Long taxInvoiceProcessId, TaxInvoiceProcessDTO processDTO);

    List<TaxInvoiceProcess> findProcessList();
}
