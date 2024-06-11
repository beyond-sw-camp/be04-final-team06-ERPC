package com.cineverse.erpc.account.account.dto;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestDeleteAccount {
    private String accountDeleteRequestReason;
    private Account account;
}
