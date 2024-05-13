package com.cineverse.erpc.quotation.quotation.repo;

import com.cineverse.erpc.quotation.quotation.aggregate.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
