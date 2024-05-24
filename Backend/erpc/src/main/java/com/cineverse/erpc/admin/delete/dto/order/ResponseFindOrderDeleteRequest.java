package com.cineverse.erpc.admin.delete.dto.order;

import com.cineverse.erpc.order.order.aggregate.Order;
import lombok.Data;

@Data
public class ResponseFindOrderDeleteRequest {
    private long orderDeleteRequestId;
    private String orderDeleteRequestReason;
    private String orderDeleteRequestStatus;
    private Order order;
}
