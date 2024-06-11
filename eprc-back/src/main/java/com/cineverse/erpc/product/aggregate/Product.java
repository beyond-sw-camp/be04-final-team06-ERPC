package com.cineverse.erpc.product.aggregate;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_product")
@Builder
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @Column(name = "product_main_category")
    private String productMainCategory;

    @Column(name = "product_sub_category")
    private String productSubCategory;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_unit")
    private String productUnit;

    @Column(name = "product_price")
    private long productPrice;

    @Column(name = "product_status")
    private String productStatus;

    @Column(name = "product_inventory")
    private String productInventory;
}
