package com.cineverse.erpc.slip.taxinvoice.aggreagte;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_tax_invoice_file")
public class TaxInvoiceFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private long fileId;

    @Column(name = "origin_name")
    private String originName;

    @Column(name = "access_url")
    private String accessUrl;

    @Column(name = "upload_date")
    private String uploadDate;

    @Column(name = "upload_user")
    private String uploadUser;

    @Column(name = "tax_invoice_request_id")
    private long taxInvoiceRequestId;
}
