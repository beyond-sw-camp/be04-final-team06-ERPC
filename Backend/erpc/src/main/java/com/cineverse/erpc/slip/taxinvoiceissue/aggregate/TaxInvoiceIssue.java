package com.cineverse.erpc.slip.taxinvoiceissue.aggregate;

import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceRequestStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_tax_invoice_issue")
public class TaxInvoiceIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tax_invoice_issue_id")
    private long taxInvoiceIssueId;

    @Column(name = "tax_invoice_reject_reason")
    private String taxInvoiceRejectReason;

    @Column(name = "tax_invoice_issue_date")
    private String taxInvoiceIssueDate;

    @ManyToOne
    @JoinColumn(name = "tax_invoice_request_status_id")
    private TaxInvoiceRequestStatus taxInvoiceRequestStatus;

    @Column(name = "tax_invoice_request")
    private long taxInvoiceRequest;
}
