package com.cineverse.erpc.approval.aggregate;

import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_quotation_approval")
public class QuotationApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quotation_approval_id")
    private long quotationApprovalId;

    @Column(name = "approval_request_date")
    private String approvalRequestDate;

    @Column(name = "approval_date")
    private String approvalDate;

    @Column(name = "approval_content")
    private String approvalContent;

    @JoinColumn(name = "quotation_id")
    @ManyToOne
    private Quotation quotation;

    @JoinColumn(name = "approval_status_id")
    @ManyToOne
    private ApprovalStatus approvalStatus;
}
