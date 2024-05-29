package com.cineverse.erpc.admin.delete.dto.account;

import com.cineverse.erpc.account.account.aggregate.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestAccountDeleteRequestProcess {
    private long accountDeleteRequestId;
}
