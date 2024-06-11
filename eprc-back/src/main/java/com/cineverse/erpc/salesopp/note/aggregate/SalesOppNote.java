package com.cineverse.erpc.salesopp.note.aggregate;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOpp;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_sales_opportunity_note")
@Builder
public class SalesOppNote {
    @Id
    @Column(name = "sales_opp_note_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long salesOppNoteId;

    @Column(name = "sales_opp_note")
    private String salesOppNote;

    @Column(name = "sales_opp_note_date")
    private String salesOppNoteDate;

    @Column(name = "sales_opp_note_delete_date")
    private String salesOppNoteDeleteDate;

    @JoinColumn(name = "sales_opp_id")
    @ManyToOne
    private SalesOpp salesOpp;

    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;
}
