package com.cineverse.erpc.shipment.aggregate;

import com.cineverse.erpc.order.order.aggregate.ShipmentStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_shipment")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_id")
    private long shipmentId;

    @Column(name = "order_due_date")
    private String orderDueDate;

    @Column(name = "estimated_delivery_date")
    private String estimatedDeliveryDate;

    @Column(name = "estimated_arrive_date")
    private String estimatedArriveDate;

    @Column(name = "arrive_date")
    private String arriveDate;

    @Column(name = "estimated_release_date")
    private String estimatedReleaseDate;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "transaction_code")
    private String transactionCode;

    @JoinColumn(name = "shipment_status_id")
    @ManyToOne
    private ShipmentStatus shipmentStatus;
}
