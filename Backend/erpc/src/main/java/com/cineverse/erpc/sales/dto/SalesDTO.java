package com.cineverse.erpc.sales.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SalesDTO {
    private long employeeSales;
    private long teamSales;
    private long totalSales;
}
