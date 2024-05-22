package com.cineverse.erpc.approval.dto.quotation;

import com.cineverse.erpc.approval.aggregate.ApprovalStatus;
import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import lombok.Data;

@Data
public class RequestQuotationApprovalProcess {
    private long quotationApprovalId;
    private String approvalContent;
    private ApprovalStatus approvalStatus;
}
