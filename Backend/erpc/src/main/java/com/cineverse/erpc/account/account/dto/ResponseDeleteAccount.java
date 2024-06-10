package com.cineverse.erpc.account.account.dto;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import lombok.Data;

@Data
public class ResponseDeleteAccount {
    private long accountDeleteRequestId;
    private String accountDeleteRequestReason;
    private String accountDeleteRequestStatus;
    private Account account;
}
