package com.cineverse.erpc.slip.taxinvoice.aggreagte;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_tax_invoice_request_status")
public class TaxInvoiceRequestStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tax_invoice_request_status_id")
    private int taxInvoiceRequestStatusId;

    @Column(name = "tax_invoice_request_status")
    private String taxInvoiceRequestStatus;
}
