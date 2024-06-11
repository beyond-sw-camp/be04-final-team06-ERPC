package com.cineverse.erpc.contract.aggregate;

import com.cineverse.erpc.product.aggregate.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_contract_product")
public class ContractProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_product_id")
    private long contractProductId;

    @Column(name = "contract_product_count")
    private int contractProductCount;

    @Column(name = "contract_supply_price")
    private long contractSupplyPrice;

    @Column(name = "contract_production_note")
    private String contractProductionNote;

    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;

    @JoinColumn(name = "contract_id")
    @ManyToOne
    @JsonIgnore
    private Contract contract;
}
