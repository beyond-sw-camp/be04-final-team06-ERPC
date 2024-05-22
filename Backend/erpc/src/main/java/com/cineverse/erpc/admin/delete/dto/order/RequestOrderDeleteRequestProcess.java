package com.cineverse.erpc.admin.delete.dto.order;

import com.cineverse.erpc.order.order.aggregate.Order;
import lombok.Data;

@Data
public class RequestOrderDeleteRequestProcess {
    private long orderDeleteRequestId;
}
