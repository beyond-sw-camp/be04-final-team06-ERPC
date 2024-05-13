package com.cineverse.erpc.contract.aggregate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_contract_category")
public class ContractCategory {
    @Id
    @Column(name = "contract_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contractCategoryId;

    @Column(name = "contract_category")
    private String contractCategory;
}
