package com.cineverse.erpc.approval.dto.shipment;

import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.order.order.aggregate.Order;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestRegistShipmentApproval {

    private String approvalContent;
    private Order order;
}
