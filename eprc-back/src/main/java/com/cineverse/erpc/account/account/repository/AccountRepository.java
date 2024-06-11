package com.cineverse.erpc.account.account.repository;

import com.cineverse.erpc.account.account.aggregate.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByAccountDeleteDateIsNull();
}
