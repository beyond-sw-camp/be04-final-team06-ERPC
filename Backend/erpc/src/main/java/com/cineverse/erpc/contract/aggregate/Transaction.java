package com.cineverse.erpc.contract.aggregate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_transaction")
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name = "transaction_date")
    private String transactionDate;

    @Column(name = "transaction_code")
    private String transactionCode;
}
