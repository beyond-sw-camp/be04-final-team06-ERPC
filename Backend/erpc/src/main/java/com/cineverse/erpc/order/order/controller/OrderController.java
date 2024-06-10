package com.cineverse.erpc.order.order.controller;

import com.cineverse.erpc.order.order.aggregate.Order;
import com.cineverse.erpc.order.order.dto.*;
import com.cineverse.erpc.order.order.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
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
            @RequestPart("order") String orderJson,
            @RequestPart(value = "files", required = false) MultipartFile[] files) throws JsonProcessingException {

        String utf8Json = new String(orderJson.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();

        RequestRegistOrderDTO newOrder = objectMapper.readValue(utf8Json, RequestRegistOrderDTO.class);

        orderService.registOrder(newOrder, files);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseRegistOrderDTO responseOrder = mapper.map(newOrder, ResponseRegistOrderDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

    /* 단일조회 */
    @GetMapping("/{orderId}")
    public OrderDTO findOrderByOrderId(@PathVariable long orderId) {
        OrderDTO order = orderService.findOrderById(orderId);

        return order;
    }

    /* 다중조회 */
    @GetMapping("")
    public List<ResponseOrderLists> findAllOrders() {
        return orderService.findAllOrders();
    }

    /* 수정 */
    @PatchMapping("/modify/{orderId}")
    public ResponseEntity<ResponseModifyOrder> modifyOrder(@RequestPart("order") String orderJson,
                                                           @RequestPart(value = "files", required = false) MultipartFile[] files,
                                                           @PathVariable long orderId) throws JsonProcessingException {

        String utf8Json = new String(orderJson.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();
        RequestModifyOrder requestModifyOrder = objectMapper.readValue(utf8Json, RequestModifyOrder.class);

        ResponseModifyOrder responseModifyOrder = orderService.modifyOrder(orderId, requestModifyOrder, files);

        return ResponseEntity.ok().body(responseModifyOrder);
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseDeleteOrder> deleteOrder (@RequestBody RequestDeleteOrder requestDeleteOrder) {

        ResponseDeleteOrder responseDeleteOrder = orderService.deleteOrder(requestDeleteOrder);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDeleteOrder);
    }
}
