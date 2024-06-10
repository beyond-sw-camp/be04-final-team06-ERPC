package com.cineverse.erpc.approval.aggregate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_approval_status")
public class ApprovalStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approval_status_id")
    private int approvalStatusId;

    @Column(name = "approval_status")
    private String approvalStatus;
}
