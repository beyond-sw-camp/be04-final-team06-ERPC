package com.cineverse.erpc.contract.aggregate;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.file.aggregate.ContractFile;
import com.cineverse.erpc.quotation.quotation.aggregate.Transaction;
import com.cineverse.erpc.warehouse.aggregate.Warehouse;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private long contractId;

    @Column(name = "contract_date")
    private String contractDate;

    @Column(name = "contract_delete_date")
    private String contractDeleteDate;

    @Column(name = "contract_note")
    private String contractNote;

    @Column(name = "contract_total_price")
    private Long contractTotalPrice;

    @Column(name = "contract_due_date")
    private String contractDueDate;

    @Column(name = "down_payment")
    private Long downPayment;

    @Column(name = "progress_payment")
    private Long progressPayment;

    @Column(name = "balance")
    private Long balance;

    @Column(name = "contract_code")
    private String contractCode;

    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;

    @JoinColumn(name = "transaction_id")
    @ManyToOne
    private Transaction transaction;

    @JoinColumn(name = "account_id")
    @ManyToOne
    private Account account;

    @JoinColumn(name = "warehouse_id")
    @ManyToOne
    private Warehouse warehouse;

    @JoinColumn(name = "contract_category_id")
    @ManyToOne
    private ContractCategory contractCategory;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ContractProduct> contractProduct = new ArrayList<>();

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ContractFile> contractFile = new ArrayList<>();
}
