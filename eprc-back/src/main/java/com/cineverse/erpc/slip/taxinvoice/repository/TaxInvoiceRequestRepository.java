package com.cineverse.erpc.slip.taxinvoice.repository;

import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxInvoiceRequestRepository extends JpaRepository<TaxInvoiceRequest, Long> {
    List<TaxInvoiceRequest> findAllByOrderByTaxInvoiceRequestIdDesc();

    TaxInvoiceRequest findByTaxInvoiceRequestId(long taxInvoiceRequestId);
}
