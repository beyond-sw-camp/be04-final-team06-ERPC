package com.cineverse.erpc.slip.taxinvoice.repository;

import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceProcess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxInvoiceProcessRepository extends JpaRepository<TaxInvoiceProcess, Long> {
}
