package com.cineverse.erpc.salesopp.opportunity.aggregate;

import com.cineverse.erpc.contract.aggregate.Contract;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_sales_opportunity_delete_request")
@Builder
public class SalesOppDeleteRequest {
    @Id
    @Column(name = "sales_opp_delete_request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long salesOppDeleteRequestId;

    @Column(name = "sales_opp_delete_request_reason")
    private String salesOppDeleteRequestReason;

    @Column(name = "sales_opp_delete_request_status")
    private char salesOppDeleteRequestStatus;

    @JoinColumn(name = "sales_opp_id")
    @OneToOne
    @JsonIgnore
    private SalesOpp salesOpp;
}
