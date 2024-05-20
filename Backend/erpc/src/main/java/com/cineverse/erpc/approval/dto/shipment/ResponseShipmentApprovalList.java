package com.cineverse.erpc.approval.dto.shipment;

import com.cineverse.erpc.approval.aggregate.ApprovalStatus;
import com.cineverse.erpc.approval.aggregate.ContractApproval;
import com.cineverse.erpc.approval.aggregate.QuotationApproval;
import com.cineverse.erpc.approval.aggregate.ShipmentApproval;
import com.cineverse.erpc.order.order.aggregate.Order;
import lombok.Data;

@Data
public class ResponseShipmentApprovalList {
    private long shipmentApprovalId;
    private String approvalRequestDate;
    private String approvalDate;
    private String approvalContent;
    private Order order;
    private ApprovalStatus approvalStatus;
}
