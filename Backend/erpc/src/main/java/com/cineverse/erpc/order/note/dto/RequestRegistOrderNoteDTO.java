package com.cineverse.erpc.order.note.dto;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.order.order.aggregate.Order;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestRegistOrderNoteDTO {
    private String orderNote;
    private Order order;
    private Employee employee;
}
