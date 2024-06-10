package com.cineverse.erpc.approval.dto.contract;

import com.cineverse.erpc.approval.aggregate.ApprovalStatus;
import com.cineverse.erpc.approval.aggregate.ContractApproval;
import com.cineverse.erpc.approval.aggregate.QuotationApproval;
import com.cineverse.erpc.approval.aggregate.ShipmentApproval;
import com.cineverse.erpc.contract.aggregate.Contract;
import lombok.Data;

@Data
public class ResponseContractApprovalList {
    private long contractApprovalId;
    private String approvalRequestDate;
    private String approvalDate;
    private String approvalContent;
    private Contract contract;
    private ApprovalStatus approvalStatus;

}
