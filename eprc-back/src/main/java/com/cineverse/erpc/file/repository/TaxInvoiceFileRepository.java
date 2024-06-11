package com.cineverse.erpc.file.repository;

import com.cineverse.erpc.file.aggregate.TaxInvoiceFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxInvoiceFileRepository extends JpaRepository<TaxInvoiceFile, Long> {

    List<TaxInvoiceFile> findByTaxInvoiceRequest_TaxInvoiceRequestId(long taxInvoiceRequestId);
}
