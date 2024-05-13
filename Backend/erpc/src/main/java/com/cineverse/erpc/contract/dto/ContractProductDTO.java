package com.cineverse.erpc.contract.dto;

import com.cineverse.erpc.product.aggregate.entity.Product;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ContractProductDTO {
    private Long contractProductId;
    private int contractProductCount;
    private String contractSupplyPrice;
    private Product product;
    private int contractId;
}
