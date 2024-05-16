package com.cineverse.erpc.order.order.dto;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.contract.aggregate.ContractCategory;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.order.order.aggregate.OrderProduct;
import com.cineverse.erpc.order.order.aggregate.ShipmentStatus;
import com.cineverse.erpc.quotation.quotation.aggregate.Transaction;
import com.cineverse.erpc.warehouse.aggregate.Warehouse;
import lombok.Data;

import java.util.List;

@Data
public class RequestRegistOrderDTO {

    private String contactDate;
    private long orderTotalPrice;
    private String orderDueDate;
    private String orderNote;
    private Account account;
    private Employee employee;
    private Transaction transaction;
    private Warehouse warehouse;
    private ContractCategory contractCategory;
    private List<OrderProduct> orderProduct;
}
