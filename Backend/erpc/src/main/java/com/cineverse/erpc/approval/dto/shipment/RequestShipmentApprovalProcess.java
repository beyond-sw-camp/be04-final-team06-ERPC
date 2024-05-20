package com.cineverse.erpc.approval.dto.shipment;

import com.cineverse.erpc.approval.aggregate.ApprovalStatus;
import com.cineverse.erpc.order.order.aggregate.Order;
import lombok.Data;

@Data
public class RequestShipmentApprovalProcess {
    private long shipmentApprovalId;
    private ApprovalStatus approvalStatus;
}
