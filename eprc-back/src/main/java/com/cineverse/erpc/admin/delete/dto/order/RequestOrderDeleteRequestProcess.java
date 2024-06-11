package com.cineverse.erpc.admin.delete.dto.order;

import com.cineverse.erpc.order.order.aggregate.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestOrderDeleteRequestProcess {
    private long orderDeleteRequestId;
}
