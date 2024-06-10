package com.cineverse.erpc.account.account.aggregate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_account_delete_request")
public class AccountDeleteRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_delete_request_id")
    private long accountDeleteRequestId;

    @Column(name = "account_delete_request_reason")
    private String accountDeleteRequestReason;

    @Column(name = "account_delete_request_status")
    private String accountDeleteRequestStatus;

    @JoinColumn(name = "account_id")
    @ManyToOne
    private Account account;
}
