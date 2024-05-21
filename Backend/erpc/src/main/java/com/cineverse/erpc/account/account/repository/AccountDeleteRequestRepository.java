package com.cineverse.erpc.account.account.repository;

import com.cineverse.erpc.account.account.aggregate.AccountDeleteRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDeleteRequestRepository extends JpaRepository<AccountDeleteRequest, Long> {
}
