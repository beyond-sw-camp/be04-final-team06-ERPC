package com.cineverse.erpc.order.order.dto;

import com.cineverse.erpc.order.order.aggregate.Order;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestDeleteOrder {
    private String orderDeleteRequestReason;
    private Order order;
}
