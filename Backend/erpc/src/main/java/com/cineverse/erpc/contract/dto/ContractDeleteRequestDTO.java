package com.cineverse.erpc.contract.dto;

import com.cineverse.erpc.contract.aggregate.Contract;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ContractDeleteRequestDTO {
    private Long contractDeleteRequestId;
    private String contractDeleteRequestReason;
    private String contractDeleteRequestStatus;
    private Contract contract;
}
