package com.cineverse.erpc.sales.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SalesDTO {
    private Long employeeSales;
    private Long teamSales;
    private Long totalSales;
}
