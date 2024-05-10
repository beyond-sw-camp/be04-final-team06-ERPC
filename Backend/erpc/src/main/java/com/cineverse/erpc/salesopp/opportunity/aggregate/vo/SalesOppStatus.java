package com.cineverse.erpc.salesopp.opportunity.aggregate.vo;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_sales_opportunity_status")
@Builder
public class SalesOppStatus {
    @Id
    @Column(name = "sales_opportunity_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salesOpportunityStatusId;

    @Column(name = "sales_opportunity_status")
    private int salesOpportunityStatus;

    @Column(name = "sales_opp_id")
    private int salesOppId;
}
