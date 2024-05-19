package com.cineverse.erpc.approval.aggregate;

import com.cineverse.erpc.contract.aggregate.Contract;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_contract_approval")
public class ContractApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_approval_id")
    private long contractApprovalId;

    @Column(name = "approval_date")
    private String ApprovalDate;

    @Column(name = "approval_content")
    private String ApprovalContent;

    @JoinColumn(name = "contract_id")
    @ManyToOne
    private Contract contract;

    @JoinColumn(name = "approval_status_id")
    @ManyToOne
    private ApprovalStatus approvalStatus;
}
