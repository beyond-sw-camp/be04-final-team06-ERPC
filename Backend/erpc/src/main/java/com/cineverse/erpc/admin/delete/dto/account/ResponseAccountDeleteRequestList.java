package com.cineverse.erpc.admin.delete.dto.account;

import com.cineverse.erpc.account.account.aggregate.Account;
import lombok.Data;

@Data
public class ResponseAccountDeleteRequestList {
    private long accountDeleteRequestId;
    private String accountDeleteRequestReason;
    private String accountDeleteRequestStatus;
    private Account account;
}
