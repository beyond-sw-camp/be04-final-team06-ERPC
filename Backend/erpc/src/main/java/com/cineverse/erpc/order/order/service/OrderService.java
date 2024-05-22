package com.cineverse.erpc.order.order.service;

import com.cineverse.erpc.order.order.dto.*;

import java.util.List;

public interface OrderService {
    void registOrder(RequestRegistOrderDTO requestOrder);

    OrderDTO findOrderById(long orderId);

    List<ResponseOrderLists> findAllOrders();

    ResponseModifyOrder modifyOrder(long orderId, RequestModifyOrder requestModifyOrder);

    ResponseDeleteOrder deleteOrder(RequestDeleteOrder requestDeleteOrder);
}
