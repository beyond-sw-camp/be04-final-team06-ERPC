package com.cineverse.erpc.contract.aggregate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
@Table(name = "tbl_contract_delete_request")
public class ContractDeleteRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_delete_request_id")
    private long contractDeleteRequestId;

    @Column(name = "contract_delete_request_reason")
    private String contractDeleteRequestReason;

    @Column(name = "contract_delete_request_status")
    private char contractDeleteRequestStatus;

    @JoinColumn(name = "contract_id")
    @ManyToOne
    private Contract contract;
}
