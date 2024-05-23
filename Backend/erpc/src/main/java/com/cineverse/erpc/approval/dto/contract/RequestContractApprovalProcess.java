package com.cineverse.erpc.approval.dto.contract;

import com.cineverse.erpc.approval.aggregate.ApprovalStatus;
import com.cineverse.erpc.contract.aggregate.Contract;
import lombok.Data;

@Data
public class RequestContractApprovalProcess {
    private long contractApprovalId;
    private String approvalContent;
    private ApprovalStatus approvalStatus;
}
