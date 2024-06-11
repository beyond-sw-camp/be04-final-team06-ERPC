package com.cineverse.erpc.order.order.dto;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.contract.aggregate.ContractCategory;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.file.aggregate.OrderFile;
import com.cineverse.erpc.order.order.aggregate.OrderProduct;
import com.cineverse.erpc.order.order.aggregate.ShipmentStatus;
import com.cineverse.erpc.quotation.quotation.aggregate.Transaction;
import com.cineverse.erpc.warehouse.aggregate.Warehouse;
import lombok.Data;

import java.util.List;

@Data
public class ResponseOrderLists {
    private long orderRegistrationId;
    private long orderTotalPrice;
    private String depositDate;
    private String orderDate;
    private String orderDeleteDate;
    private Account account;
    private Employee employee;
    private Transaction transaction;
    private ShipmentStatus shipmentStatus;
    private List<OrderFile> orderFile;
}
