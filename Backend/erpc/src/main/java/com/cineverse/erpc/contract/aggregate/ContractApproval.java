package com.cineverse.erpc.contract.aggregate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_contract_approval")
public class ContractApproval {
    @Id
    @Column(name = "contract_approval_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractApprovalId;

    @Column(name = "approval_date")
    private String approvalDate;

    @Column(name = "approval_content")
    private String approvalContent;

    @Column(name = "contract_id")
    private int contractId;

    @JoinColumn(name = "approval_status_id")
    @ManyToOne
    private ApprovalStatus approvalStatus;
}
