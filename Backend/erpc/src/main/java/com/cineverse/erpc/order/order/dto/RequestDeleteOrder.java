package com.cineverse.erpc.order.order.dto;

import com.cineverse.erpc.order.order.aggregate.Order;
import lombok.Data;

@Data
public class RequestDeleteOrder {
    private String orderDeleteRequestReason;
    private Order order;
}
