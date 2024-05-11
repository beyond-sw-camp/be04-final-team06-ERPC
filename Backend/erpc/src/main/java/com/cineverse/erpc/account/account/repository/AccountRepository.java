package com.cineverse.erpc.account.account.repository;

import com.cineverse.erpc.account.account.aggregate.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
