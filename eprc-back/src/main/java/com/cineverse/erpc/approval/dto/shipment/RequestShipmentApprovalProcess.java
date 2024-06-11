package com.cineverse.erpc.approval.dto.shipment;

import com.cineverse.erpc.approval.aggregate.ApprovalStatus;
import com.cineverse.erpc.order.order.aggregate.Order;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestShipmentApprovalProcess {
    private long shipmentApprovalId;
    private String approvalContent;
    private ApprovalStatus approvalStatus;
}
