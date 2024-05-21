package com.cineverse.erpc.account.account.dto;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import lombok.Data;

@Data
public class ResponseDeleteAccount {
    private long quotationDeleteRequestId;
    private String quotationDeleteRequestReason;
    private String quotationDeleteRequestStatus;
    private Account account;
}
