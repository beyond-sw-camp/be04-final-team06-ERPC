package com.cineverse.erpc.approval.aggregate;

import com.cineverse.erpc.order.order.aggregate.Order;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_shipment_approval")
public class ShipmentApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_approval_id")
    private long shipmentApprovalId;

    @Column(name = "approval_request_date")
    private String approvalRequestDate;

    @Column(name = "approval_date")
    private String approvalDate;

    @Column(name = "approval_content")
    private String approvalContent;

    @JoinColumn(name = "order_registration_id")
    @ManyToOne
    private Order order;

    @JoinColumn(name = "approval_status_id")
    @ManyToOne
    private ApprovalStatus approvalStatus;
}
