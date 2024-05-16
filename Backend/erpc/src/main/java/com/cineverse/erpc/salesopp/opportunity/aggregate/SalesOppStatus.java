package com.cineverse.erpc.salesopp.opportunity.aggregate;

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
    @Column(name = "sales_opp_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salesOppStatusId;

    @Column(name = "sales_opp_status")
    private String salesOppStatus;
}
