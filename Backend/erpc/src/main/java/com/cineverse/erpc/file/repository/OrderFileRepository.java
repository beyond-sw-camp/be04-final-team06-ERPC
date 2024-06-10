package com.cineverse.erpc.file.repository;

import com.cineverse.erpc.file.aggregate.OrderFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderFileRepository extends JpaRepository<OrderFile, Long> {
    List<OrderFile> findByOrder_OrderRegistrationId(long orderRegistrationId);
}
