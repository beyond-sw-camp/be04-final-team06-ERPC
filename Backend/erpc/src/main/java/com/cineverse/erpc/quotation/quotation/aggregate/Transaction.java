package com.cineverse.erpc.quotation.quotation.aggregate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private long transactionId;

    @Column(name = "transaction_date")
    private String transactionDate;

    @Column(name = "transaction_code")
    private String transactionCode;
}
