package com.cineverse.erpc.warehouse.aggregate;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_warehouse")
@Builder
public class Warehouse {
    @Id
    @Column(name = "warehouse_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long warehouseId;

    @Column(name = "warehouse_name")
    private String warehouseName;

    @Column(name = "warehouse_location")
    private String warehouseLocation;

    @Column(name = "warehouse_code")
    private String warehouseCode;

    @Column(name = "warehouse_type")
    private String warehouseType;

    @Column(name = "warehouse_usage")
    private String warehouseUsage;

    @Column(name = "production_line_name")
    private String productionLineName;

    @Column(name = "outsource_name")
    private String outsourceName;
}
