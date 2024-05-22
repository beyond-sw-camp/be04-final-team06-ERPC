package com.cineverse.erpc.order.order.aggregate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_orderDelete_Request")
public class OrderDeleteRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_delete_request_id")
    private long orderDeleteRequestId;

    @Column(name = "order_delete_request_reason")
    private String orderDeleteRequestReason;

    @Column(name = "order_delete_request_status")
    private String orderDeleteRequestStatus;

    @JoinColumn(name = "order_registration_id")
    @ManyToOne
    private Order order;
}
