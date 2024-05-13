package com.cineverse.erpc.contract.aggregate;

import com.cineverse.erpc.product.aggregate.entity.Product;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_contract_product")
public class ContractProduct {

    @Id
    @Column(name = "contract_product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractProductId;

    @Column(name = "contract_product_count")
    private int contractProductCount;

    @Column(name = "contract_supply_price")
    private long contractSupplyPrice;

    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;

    @Column(name = "contract_id")
    private long contractId;
}
