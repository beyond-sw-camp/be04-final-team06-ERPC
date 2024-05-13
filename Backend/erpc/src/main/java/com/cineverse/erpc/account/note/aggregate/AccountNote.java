package com.cineverse.erpc.account.note.aggregate;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.employee.aggregate.Employee;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_account_note")
public class AccountNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_note_id")
    private long accountNoteId;

    @Column(name = "account_note")
    private String accountNote;

    @Column(name = "account_note_date")
    private String accountNoteDate;

    @Column(name = "account_delete_date")
    private String accountDeleteDate;

    @JoinColumn(name = "account_id")
    @ManyToOne
    private Account account;

    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;
}
