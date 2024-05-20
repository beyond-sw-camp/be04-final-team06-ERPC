package com.cineverse.erpc.salesopp.opportunity.dto;

import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOpp;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SalesOppDeleteRequestDTO {
    private int salesOppDeleteRequestId;
    private String requestContent;
    private char requestStatus;
    private SalesOpp salesOpp;
}
