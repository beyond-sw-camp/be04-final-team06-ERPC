package com.cineverse.erpc.slip.taxinvoice.aggreagte;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.file.aggregate.TaxInvoiceFile;
import com.cineverse.erpc.order.order.aggregate.Order;
import com.cineverse.erpc.order.order.aggregate.ShipmentStatus;
import com.cineverse.erpc.slip.collection.aggregate.Collection;
import com.cineverse.erpc.slip.taxinvoiceissue.aggregate.TaxInvoiceIssue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_tax_invoice_request")
public class TaxInvoiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tax_invoice_request_id")
    private long taxInvoiceRequestId;

    @Column(name = "tax_invoice_request_date")
    private String taxInvoiceRequestDate;

    @Column(name = "tax_invoice_note")
    private String taxInvoiceNote;

    @JoinColumn(name = "order_registration_id")
    @ManyToOne
    @JsonIgnore
    private Order order;

    @JoinColumn(name = "account_id")
    @ManyToOne
    @JsonIgnore
    private Account account;

    @JoinColumn(name = "employee_id")
    @ManyToOne
    @JsonIgnore
    private Employee employee;

    @JoinColumn(name = "collection_id")
    @ManyToOne
    @JsonIgnore
    private Collection collection;

    @JoinColumn(name = "tax_invoice_request_status_id")
    @ManyToOne
    @JsonIgnore
    private TaxInvoiceRequestStatus taxInvoiceRequestStatus;

    @OneToMany(mappedBy = "taxInvoiceRequest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TaxInvoiceFile> taxInvoiceFile = new ArrayList<>();
}
