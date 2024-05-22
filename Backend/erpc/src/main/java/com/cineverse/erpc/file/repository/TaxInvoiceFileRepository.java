package com.cineverse.erpc.file.repository;

import com.cineverse.erpc.file.aggregate.TaxInvoiceFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxInvoiceFileRepository extends JpaRepository<TaxInvoiceFile, Long> {
}
