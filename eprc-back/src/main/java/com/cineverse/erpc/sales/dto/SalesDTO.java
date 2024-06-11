package com.cineverse.erpc.sales.dto;

import lombok.*;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SalesDTO {
    private Map<Integer, Map<String, Long>> sales;
}
