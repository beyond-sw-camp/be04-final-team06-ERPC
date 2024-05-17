package com.cineverse.erpc.slip.collection.aggregate;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_deposit_category")
public class DepositCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deposit_category_id")
    private int depositCategoryId;

    @Column(name = "deposit_category")
    private String depositCategory;
}
