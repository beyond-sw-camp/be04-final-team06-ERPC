package com.cineverse.erpc.product.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductDTO {
    private long productId;
    private String productMainCategory;
    private String productSubCategory;
    private String productCode;
    private String productName;
    private String productUnit;
    private long productPrice;
    private String productStatus;
    private String productInventory;
}
