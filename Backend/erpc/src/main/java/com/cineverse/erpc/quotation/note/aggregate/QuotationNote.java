package com.cineverse.erpc.quotation.note.aggregate;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_quotation_note")
public class QuotationNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quotation_note_id")
    private long quotationNoteId;

    @Column(name = "quotation_note")
    private String quotationNote;

    @Column(name = "quotation_note_date")
    private String quotationNoteDate;

    @Column(name = "quotation_delete_date")
    private String quotationDeleteDate;

    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;

    @JoinColumn(name = "quotation_id")
    @ManyToOne
    private Quotation quotation;


}
