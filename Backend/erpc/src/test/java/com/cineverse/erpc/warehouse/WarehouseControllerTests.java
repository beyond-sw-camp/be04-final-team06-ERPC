package com.cineverse.erpc.warehouse;

import com.cineverse.erpc.warehouse.aggregate.Warehouse;
import com.cineverse.erpc.warehouse.service.WarehouseService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WarehouseControllerTests {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseControllerTests(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Test
    @Transactional
    @DisplayName("창고 전체조회 테스트")
    public void findWarehouseList() {
        List<Warehouse> warehouseList = warehouseService.findWarehouseList();

        assertThat(warehouseList).isNotEmpty();
    }
}