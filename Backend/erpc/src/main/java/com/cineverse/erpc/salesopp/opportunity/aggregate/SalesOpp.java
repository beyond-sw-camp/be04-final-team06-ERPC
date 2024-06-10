package com.cineverse.erpc.salesopp.opportunity.aggregate;

import com.cineverse.erpc.employee.aggregate.Employee;
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

    @Column(name = "opp_account_name")
    private String oppAccountName;

    @Column(name = "opp_account_pic")
    private String oppAccountPic;

    @Column(name = "opp_account_contact")
    private String oppAccountContact;

    @Column(name = "opp_account_location")
    private String oppAccountLocation;

    @Column(name = "opp_account_email")
    private String oppAccountEmail;

    @Column(name = "opp_account_note")
    private String oppAccountNote;

    @Column(name = "opp_date")
    private String oppDate;

    @Column(name = "opp_delete_date")
    private String oppDeleteDate;

    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;

    @JoinColumn(name = "sales_opp_status_id")
    @ManyToOne
    private SalesOppStatus salesOppStatus;
}
