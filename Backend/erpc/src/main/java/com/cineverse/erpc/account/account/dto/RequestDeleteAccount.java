package com.cineverse.erpc.account.account.dto;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class RequestDeleteAccount {
    private String accountDeleteRequestReason;
    private Account account;
}
