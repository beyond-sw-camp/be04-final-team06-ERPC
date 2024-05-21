package com.cineverse.erpc.smtp.dto;

import com.cineverse.erpc.approval.aggregate.ShipmentApproval;
import lombok.Data;

@Data
public class RequestShipmentApprovalMail {
    ShipmentApproval shipmentApproval;

}
