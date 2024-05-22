package com.cineverse.erpc.order.order.controller;

import com.cineverse.erpc.order.order.aggregate.Order;
import com.cineverse.erpc.order.order.dto.*;
import com.cineverse.erpc.order.order.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private ModelMapper mapper;
    private OrderService orderService;

    @Autowired
    public OrderController(ModelMapper mapper, OrderService orderService) {
        this.mapper = mapper;
        this.orderService = orderService;
    }

    /* 생성 */
    @PostMapping("/regist")
    public ResponseEntity<ResponseRegistOrderDTO> registOrder(
            @RequestBody RequestRegistOrderDTO requestOrder) {

        orderService.registOrder(requestOrder);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseRegistOrderDTO responseOrder = mapper.map(requestOrder, ResponseRegistOrderDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

    /* 단일조회 */
    @GetMapping("/{orderId}")
    public OrderDTO findOrderByOrderId(@PathVariable long orderId) {
        OrderDTO order = orderService.findOrderById(orderId);

        return order;
    }

    /* 다중조회 */
    @GetMapping("/list")
    public List<ResponseOrderLists> findAllOrders() {
        return orderService.findAllOrders();
    }

    /* 수정 */
    @PatchMapping("/modify/{orderId}")
    public ResponseEntity<ResponseModifyOrder> modifyOrder(@PathVariable long orderId,
                                                           @RequestBody RequestModifyOrder requestModifyOrder) {
        ResponseModifyOrder responseModifyOrder =
                orderService.modifyOrder(orderId, requestModifyOrder);

        return ResponseEntity.ok().body(responseModifyOrder);
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseDeleteOrder> deleteOrder (@RequestBody RequestDeleteOrder requestDeleteOrder) {

        ResponseDeleteOrder responseDeleteOrder = orderService.deleteOrder(requestDeleteOrder);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDeleteOrder);
    }

}
