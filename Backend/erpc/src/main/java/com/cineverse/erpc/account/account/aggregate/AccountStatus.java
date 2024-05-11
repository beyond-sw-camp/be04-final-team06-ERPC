package com.cineverse.erpc.account.account.aggregate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_account_status")
public class AccountStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_status_id")
    private int accountStatusId;

    @Column(name = "account_status")
    private String accountStatus;
}
