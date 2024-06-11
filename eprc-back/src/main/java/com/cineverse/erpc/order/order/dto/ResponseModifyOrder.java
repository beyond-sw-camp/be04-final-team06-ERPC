package com.cineverse.erpc.order.order.dto;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.contract.aggregate.ContractCategory;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.file.aggregate.OrderFile;
import com.cineverse.erpc.order.order.aggregate.OrderProduct;
import com.cineverse.erpc.order.order.aggregate.ShipmentStatus;
import com.cineverse.erpc.quotation.quotation.aggregate.Transaction;
import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceRequest;
import com.cineverse.erpc.warehouse.aggregate.Warehouse;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseModifyOrder {
    private long orderRegistrationId;
    private String contactDate;
    private String estimatedDeliveryDate;
    private long orderTotalPrice;
    private String orderDueDate;
    private String estimatedArriveDate;
    private String orderDate;
    private String orderDeleteDate;
    private long totalBalance;
    private long downPayment;
    private long progressPayment;
    private long balance;
    private String arriveDate;
    private String releaseDate;
    private String estimatedReleaseDate;
    private String orderNote;
    private String depositDate;
    private Account account;
    private Employee employee;
    private Transaction transaction;
    private Warehouse warehouse;
    private ShipmentStatus shipmentStatus;
    private ContractCategory contractCategory;
    private List<OrderProduct> orderProduct;
    private List<OrderFile> orderFile;
    private List<TaxInvoiceRequest> taxInvoiceRequest;
}
