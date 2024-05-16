package com.cineverse.erpc.order.order.service;

import com.cineverse.erpc.order.order.dto.OrderDTO;
import com.cineverse.erpc.order.order.dto.RequestRegistOrderDTO;
import com.cineverse.erpc.order.order.dto.ResponseOrderLists;

import java.util.List;

public interface OrderService {
    void registOrder(RequestRegistOrderDTO requestOrder);

    OrderDTO findOrderById(long orderId);

    List<ResponseOrderLists> findAllOrders();
}
