package com.cineverse.erpc.warehouse;

import com.cineverse.erpc.warehouse.aggregate.Warehouse;
import com.cineverse.erpc.warehouse.repository.WarehouseRepository;
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
    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseControllerTests(WarehouseService warehouseService,
                                    WarehouseRepository warehouseRepository) {
        this.warehouseService = warehouseService;
        this.warehouseRepository = warehouseRepository;
    }

    @Test
    @Transactional
    @DisplayName("창고 전체조회 테스트")
    public void findWarehouseList() {
        List<Warehouse> actualWarehouseList = warehouseRepository.findAll();
        List<Warehouse> testWarehouseList = warehouseService.findWarehouseList();

        assertThat(actualWarehouseList).isEqualTo(testWarehouseList);
        assertThat(testWarehouseList).isNotEmpty();
    }
}