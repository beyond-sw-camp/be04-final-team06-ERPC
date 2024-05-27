package com.cineverse.erpc.admin.delete.dto.account;

import com.cineverse.erpc.account.account.aggregate.Account;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestAccountDeleteRequestProcess {
    private long accountDeleteRequestId;
}
