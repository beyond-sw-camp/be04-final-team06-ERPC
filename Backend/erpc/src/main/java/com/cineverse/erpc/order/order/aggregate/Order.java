package com.cineverse.erpc.order.order.aggregate;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.contract.aggregate.ContractCategory;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.file.aggregate.NoticeFile;
import com.cineverse.erpc.file.aggregate.OrderFile;
import com.cineverse.erpc.quotation.quotation.aggregate.Transaction;
import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceRequest;
import com.cineverse.erpc.warehouse.aggregate.Warehouse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_registration_id")
    private long orderRegistrationId;

    @Column(name = "contact_date")
    private String contactDate;

    @Column(name = "estimated_delivery_date")
    private String estimatedDeliveryDate;

    @Column(name = "order_total_price")
    private long orderTotalPrice;

    @Column(name = "order_due_date")
    private String orderDueDate;

    @Column(name = "estimated_arrive_date")
    private String estimatedArriveDate;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "order_delete_date")
    private String orderDeleteDate;

    @Column(name = "total_balance")
    private long totalBalance;

    @Column(name = "down_payment")
    private long downPayment;

    @Column(name = "progress_payment")
    private long progressPayment;

    @Column(name = "balance")
    private long balance;

    @Column(name = "arrive_date")
    private String arriveDate;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "estimated_release_date")
    private String estimatedReleaseDate;

    @Column(name = "order_note")
    private String orderNote;

    @Column(name = "deposit_date")
    private String depositDate;

    @JoinColumn(name = "account_id")
    @ManyToOne
    private Account account;

    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;

    @JoinColumn(name = "transaction_id")
    @ManyToOne
    private Transaction transaction;

    @JoinColumn(name = "warehouse_id")
    @ManyToOne
    private Warehouse warehouse;

    @JoinColumn(name = "shipment_status_id")
    @ManyToOne
    private ShipmentStatus shipmentStatus;

    @JoinColumn(name = "contract_category_id")
    @ManyToOne
    private ContractCategory contractCategory;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProduct;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderFile> orderFile = new ArrayList<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TaxInvoiceRequest> taxInvoiceRequest = new ArrayList<>();
}
