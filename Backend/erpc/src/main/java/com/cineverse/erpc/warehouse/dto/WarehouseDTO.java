package com.cineverse.erpc.warehouse.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class WarehouseDTO {
    private long warehouseId;
    private String warehouseName;
    private String warehouseLocation;
    private String warehouseCode;
    private String warehouseType;
    private String warehouseUsage;
    private String productionLineName;
    private String outsourceName;
}
