package com.cineverse.erpc.order.order.service;

import com.cineverse.erpc.order.order.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OrderService {
    ResponseRegistOrderDTO registOrder(RequestRegistOrderDTO requestOrder, MultipartFile[] files);

    OrderDTO findOrderById(long orderId);

    List<ResponseOrderLists> findAllOrders();

    ResponseModifyOrder modifyOrder(RequestModifyOrder requestModifyOrder, MultipartFile[] files);

    ResponseDeleteOrder deleteOrder(RequestDeleteOrder requestDeleteOrder);

    List<ResponseOrderLists> findOrderLists();
}
