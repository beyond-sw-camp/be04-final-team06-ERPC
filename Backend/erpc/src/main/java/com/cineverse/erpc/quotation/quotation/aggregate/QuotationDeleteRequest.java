package com.cineverse.erpc.quotation.quotation.aggregate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_quotation_delete_request")
public class QuotationDeleteRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quotation_delete_request_id")
    private long quotationDeleteRequestId;

    @Column(name = "quotation_delete_request_reason")
    private String quotationDeleteRequestReason;

    @Column(name = "quotation_delete_request_status")
    private String quotationDeleteRequestStatus;

    @JoinColumn(name = "quotation_id")
    @ManyToOne
    private Quotation quotation;
}
