package com.cineverse.erpc.order.order.service;

import com.cineverse.erpc.order.order.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OrderService {
    void registOrder(RequestRegistOrderDTO requestOrder, MultipartFile[] files);

    OrderDTO findOrderById(long orderId);

    List<ResponseOrderLists> findAllOrders();

    ResponseModifyOrder modifyOrder(long orderId, RequestModifyOrder requestModifyOrder);
}
