package com.cineverse.erpc.shipment.dto;

import com.cineverse.erpc.order.order.aggregate.ShipmentStatus;
import lombok.Data;

@Data
public class ShipmentDTO {
    private long shipmentId;
    private String orderDueDate;
    private String estimatedDeliveryDate;
    private String estimatedArriveDate;
    private String arriveDate;
    private String estimatedReleaseDate;
    private String releaseDate;
    private String transactionCode;
    private ShipmentStatus shipmentStatus;
}
