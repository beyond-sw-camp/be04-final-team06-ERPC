package com.cineverse.erpc.salesopp.opportunity.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SalesOppDeleteDTO {
    private int salesOppDeleteRequestId;
    private String requestContent;
    private String requestStatus;
    private int salesOppId;
}
