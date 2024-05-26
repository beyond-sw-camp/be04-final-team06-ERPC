package com.cineverse.erpc.contract.dto;

import com.cineverse.erpc.product.aggregate.Product;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ContractProductDTO {
    private long contractProductId;
    private String contractProductionNote;
    private int contractProductCount;
    private long contractSupplyPrice;
    private Product product;
    private int contractId;
}
