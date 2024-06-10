package com.cineverse.erpc.approval.dto.quotation;

import com.cineverse.erpc.approval.aggregate.ApprovalStatus;
import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import lombok.Data;

@Data
public class ResponseFindQuotationApproval {
    private long quotationApprovalId;
    private String approvalRequestDate;
    private String approvalDate;
    private String approvalContent;
    private Quotation quotation;
    private ApprovalStatus approvalStatus;
}
