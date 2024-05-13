package com.cineverse.erpc.contract.aggregate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_approval_status")
public class ApprovalStatus {
    @Id
    @Column(name = "approval_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int approvalStatusId;

    @Column(name = "approval_status")
    private String approvalStatus;
}
