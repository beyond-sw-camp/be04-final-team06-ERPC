package com.cineverse.erpc.approval.dto.shipment;

import com.cineverse.erpc.approval.aggregate.ApprovalStatus;
import com.cineverse.erpc.order.order.aggregate.Order;
import lombok.Data;

@Data
public class ResponseFindShipmentApproval {
    private long shipmentApprovalId;
    private String approvalRequestDate;
    private String approvalDate;
    private String approvalContent;
    private Order order;
    private ApprovalStatus approvalStatus;
}
