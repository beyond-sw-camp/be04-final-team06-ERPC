package com.cineverse.erpc.warehouse.service;

import com.cineverse.erpc.warehouse.aggregate.Warehouse;

import java.util.List;

public interface WarehouseService {
    List<Warehouse> findWarehouseList();
}
