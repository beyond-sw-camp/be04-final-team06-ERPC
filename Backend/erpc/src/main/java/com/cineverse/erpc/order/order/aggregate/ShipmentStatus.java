package com.cineverse.erpc.order.order.aggregate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_shipment_status")
public class ShipmentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_status_id")
    private int shipmentStatusId;

    @Column(name = "shipment_status")
    private String shipmentStatus;
}
