package com.cineverse.erpc.order.order.dto;

import com.cineverse.erpc.order.order.aggregate.Order;
import lombok.Data;

@Data
public class ResponseDeleteOrder {
    private long orderDeleteRequestId;
    private String orderDeleteRequestReason;
    private String orderDeleteRequestStatus;
    private Order order;
}
