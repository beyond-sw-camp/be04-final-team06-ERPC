package com.cineverse.erpc.slip.taxinvoice.repository;

import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxInvoiceRequestStatusRepository extends JpaRepository<TaxInvoiceRequestStatus, Integer> {
}
