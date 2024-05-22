package com.cineverse.erpc.file.repository;

import com.cineverse.erpc.file.aggregate.OrderFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFileRepository extends JpaRepository<OrderFile, Long> {
}
