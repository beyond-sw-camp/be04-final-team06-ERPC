package com.cineverse.erpc.order.order.aggregate;

import com.cineverse.erpc.product.aggregate.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_order_product")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id")
    private long orderProduct;

    @Column(name = "order_product_count")
    private int orderProductCount;

    @Column(name = "order_supply_price")
    private long orderSupplyPrice;

    @Column(name = "order_production_note")
    private String orderProductionNote;

    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;

    @JoinColumn(name = "order_registration_id")
    @ManyToOne
    @JsonIgnore
    private Order order;
}
