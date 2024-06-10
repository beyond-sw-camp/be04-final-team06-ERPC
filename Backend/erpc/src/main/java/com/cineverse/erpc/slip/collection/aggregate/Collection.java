package com.cineverse.erpc.slip.collection.aggregate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_collection")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id")
    private long collectionId;

    @Column(name = "deposit_account")
    private String depositAccount;

    @Column(name = "deposit_price")
    private long depositPrice;

    @Column(name = "deposit_date")
    private String depositDate;

    @Column(name = "deposit_pic")
    private String depositPic;

    @JoinColumn(name = "deposit_category_id")
    @ManyToOne
    private DepositCategory depositCategory;

    @Column(name = "deposit_code")
    private String depositCode;
}
