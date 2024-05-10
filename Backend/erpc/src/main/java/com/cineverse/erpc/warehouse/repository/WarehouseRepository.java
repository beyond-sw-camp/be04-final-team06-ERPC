package com.cineverse.erpc.warehouse.repository;

import com.cineverse.erpc.warehouse.aggregate.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
