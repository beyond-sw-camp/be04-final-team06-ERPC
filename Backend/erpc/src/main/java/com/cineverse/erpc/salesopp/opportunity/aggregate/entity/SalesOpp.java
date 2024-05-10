package com.cineverse.erpc.salesopp.opportunity.aggregate.entity;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.salesopp.opportunity.aggregate.vo.SalesOppStatus;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_sales_opportunity")
@Builder
public class SalesOpp {
    @Id
    @Column(name = "sales_opp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long salesOppId;

    @Column(name = "opp_accoutn_name")
    private String oppAccountName;

    @Column(name = "opp_accoutn_pic")
    private String oppAccountPic;

    @Column(name = "opp_accoutn_contact")
    private String oppAccountContact;

    @Column(name = "opp_accoutn_location")
    private String oppAccountLocation;

    @Column(name = "opp_accoutn_email")
    private String oppAccountEmail;

    @Column(name = "opp_accoutn_note")
    private String oppAccountNote;

    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;

    @JoinColumn(name = "sales_opportunity_status_id")
    @ManyToOne
    private SalesOppStatus salesOppStatus;

}
