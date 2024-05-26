package com.cineverse.erpc.shipment.repo;

import com.cineverse.erpc.shipment.aggregate.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    Shipment findByTransactionCode(String transactionCode);
}
