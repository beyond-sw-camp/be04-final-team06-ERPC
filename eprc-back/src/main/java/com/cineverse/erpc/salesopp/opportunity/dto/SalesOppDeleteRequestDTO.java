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
    private long salesOppDeleteRequestId;
    private String salesOppDeleteRequestReason;
    private char salesOppDeleteRequestStatus;
    private SalesOpp salesOpp;
}
