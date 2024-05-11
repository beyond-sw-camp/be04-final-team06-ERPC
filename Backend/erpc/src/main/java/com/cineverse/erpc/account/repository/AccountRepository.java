package com.cineverse.erpc.account.repository;

import com.cineverse.erpc.account.aggregate.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
