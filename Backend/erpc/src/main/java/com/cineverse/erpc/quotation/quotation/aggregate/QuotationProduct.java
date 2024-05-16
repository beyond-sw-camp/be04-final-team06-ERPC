package com.cineverse.erpc.quotation.quotation.aggregate;

import com.cineverse.erpc.product.aggregate.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_quotation_product")
@Data
public class QuotationProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quotation_product_id")
    private long quotationProductId;

    @Column(name = "quotation_product_count")
    private int quotationProductCount;

    @Column(name = "quotation_supply_price")
    private long quotationSupplyPrice;

    @Column(name = "quotation_production_note")
    private String quotationProductionNote;

    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;

    @JoinColumn(name = "quotation_id")
    @ManyToOne
    @JsonIgnore
    private Quotation quotation;
}
