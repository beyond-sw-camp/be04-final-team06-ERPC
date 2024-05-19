package com.cineverse.erpc.approval.dto;

import com.cineverse.erpc.approval.aggregate.ApprovalStatus;
import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.order.order.aggregate.Order;
import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import lombok.Data;

@Data
public class ResponseRegistApproval {

    private long contractApprovalId;
    private String ApprovalDate;
    private String ApprovalContent;
    private Quotation quotation;
    private Contract contract;
    private Order order;
    private ApprovalStatus approvalStatus;
}
