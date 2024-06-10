package com.cineverse.erpc.slip.taxinvoice.aggreagte;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_tax_invoice_process")
public class TaxInvoiceProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tax_invoice_process_id")
    private long taxInvoiceProcessId;

    @Column(name = "tax_invoice_process_content")
    private String taxInvoiceProcessContent;

    @Column(name = "tax_invoice_process_date")
    private String taxInvoiceProcessDate;

    @JoinColumn(name = "tax_invoice_request_id")
    @OneToOne
    @JsonIgnore
    private TaxInvoiceRequest taxInvoiceRequest;

    @ManyToOne
    @JoinColumn(name = "tax_invoice_request_status_id")
    private TaxInvoiceRequestStatus taxInvoiceRequestStatus;
}
