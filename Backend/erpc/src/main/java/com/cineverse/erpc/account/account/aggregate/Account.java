package com.cineverse.erpc.account.account.aggregate;

import com.cineverse.erpc.employee.aggregate.Employee;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private long accountId;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "corporation_num")
    private String corporationNum;

    @Column(name = "account_representative")
    private String accountRepresentative;   // 대표

    @Column(name = "corporation_status")
    private String corporationStatus;       // 법인여부

    @Column(name = "account_location")
    private String accountLocation;

    @Column(name = "account_contact")
    private String accountContact;          // 전화번호

    @Column(name = "account_email")
    private String accountEmail;

    @Column(name = "account_note")
    private String accountNote;             // 비고

    @Column(name = "account_type")
    private String accountType;             // 업종

    @Column(name = "account_code")
    private String accountCode;

    @Column(name = "account_delete_date")
    private String accountDeleteDate;

    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;

    @JoinColumn(name = "account_status_id")
    @ManyToOne
    private AccountStatus accountStatus;
}
