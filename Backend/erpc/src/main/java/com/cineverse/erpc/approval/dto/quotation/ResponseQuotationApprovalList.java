package com.cineverse.erpc.approval.dto.quotation;

import com.cineverse.erpc.approval.aggregate.ApprovalStatus;
import com.cineverse.erpc.approval.aggregate.ContractApproval;
import com.cineverse.erpc.approval.aggregate.QuotationApproval;
import com.cineverse.erpc.approval.aggregate.ShipmentApproval;
import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import lombok.Data;

@Data
public class ResponseQuotationApprovalList {
    private long quotationApprovalId;
    private String approvalRequestDate;
    private String approvalDate;
    private String approvalContent;
    private Quotation quotation;
    private ApprovalStatus approvalStatus;

}
