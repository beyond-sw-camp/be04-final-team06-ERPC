package com.cineverse.erpc.quotation.quotation.aggregate;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.file.aggregate.QuotationFile;
import com.cineverse.erpc.warehouse.aggregate.Warehouse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_quotation")
public class Quotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quotation_id")
    private long quotationId;

    @Column(name = "quotation_date")
    private String quotationDate;

    @Column(name = "quotation_delete_date")
    private String quotationDeleteDate;

    @Column(name = "quotation_note")
    private String quotationNote;

    @Column(name = "quotation_total_cost")
    private long quotationTotalCost;

    @Column(name = "quotation_due_date")
    private String quotationDueDate;

    @Column(name = "quotation_code")
    private String quotationCode;

    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;

    @JoinColumn(name = "account_id")
    @ManyToOne
    private Account account;

    @JoinColumn(name = "transaction_id")
    @ManyToOne
    private Transaction transaction;

    @JoinColumn(name = "warehouse_id")
    @ManyToOne
    private Warehouse warehouse;

    @OneToMany(mappedBy = "quotation")
    private List<QuotationProduct> quotationProduct;

    @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<QuotationFile> quotationFile = new ArrayList<>();
}
