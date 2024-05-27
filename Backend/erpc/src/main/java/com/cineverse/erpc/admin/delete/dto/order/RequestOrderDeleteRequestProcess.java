package com.cineverse.erpc.admin.delete.dto.order;

import com.cineverse.erpc.order.order.aggregate.Order;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestOrderDeleteRequestProcess {
    private long orderDeleteRequestId;
}
