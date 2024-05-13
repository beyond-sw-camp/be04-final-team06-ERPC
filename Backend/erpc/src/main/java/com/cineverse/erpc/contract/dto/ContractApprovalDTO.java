package com.cineverse.erpc.contract.dto;

import com.cineverse.erpc.contract.aggregate.ApprovalStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ContractApprovalDTO {
    private Long contractApprovalId;
    private String approvalDate;
    private String approvalContent;
    private int contractId;
    private ApprovalStatus approvalStatus;
}
