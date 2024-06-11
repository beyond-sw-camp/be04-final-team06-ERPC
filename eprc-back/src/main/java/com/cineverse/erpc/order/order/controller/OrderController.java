package com.cineverse.erpc.order.order.controller;

import com.cineverse.erpc.order.order.aggregate.Order;
import com.cineverse.erpc.order.order.dto.*;
import com.cineverse.erpc.order.order.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final ModelMapper mapper;
    private final OrderService orderService;

    @Autowired
    public OrderController(ModelMapper mapper, OrderService orderService) {
        this.mapper = mapper;
        this.orderService = orderService;
    }

    /* 생성 */
    @PostMapping(path = "/regist", consumes = {"multipart/form-data;charset=UTF-8"})
    @Operation(summary = "수주 등록", description = "수주를 등록합니다.")
    @ApiResponse(responseCode = "201", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력값 불일치")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseEntity<ResponseRegistOrderDTO> registOrder(
            @Parameter(required = true, description = "수주 등록 요청")
            @RequestPart("order") String orderJson,
            @Parameter(required = false, description = "수주 파일등록 요청")
            @RequestPart(value = "files", required = false) MultipartFile[] files) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        RequestRegistOrderDTO newOrder = objectMapper.readValue(orderJson, RequestRegistOrderDTO.class);

        orderService.registOrder(newOrder, files);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseRegistOrderDTO responseOrder = mapper.map(newOrder, ResponseRegistOrderDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

    /* 단일조회 */
    @GetMapping("/{orderId}")
    @Operation(summary = "수주 단일조회", description = "수주를 조회합니다")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public OrderDTO findOrderByOrderId(
            @Parameter(required = true, description = "수주 고유번호")
            @PathVariable long orderId) {
        OrderDTO order = orderService.findOrderById(orderId);

        return order;
    }

    /* 다중조회 */
    @GetMapping("")
    @Operation(summary = "수주 전체 조회", description = "전체 수주를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public List<ResponseOrderLists> findAllOrders() {
        return orderService.findOrderLists();
    }

    /* 수정 */
    @PatchMapping(path = "/modify", consumes = {"multipart/form-data;charset=UTF-8"})
    @Operation(summary = "수주 수정", description = "수주를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력값 불일치")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseEntity<ResponseModifyOrder> modifyOrder(
            @Parameter(required = true, description = "수주 수정 요청")
            @RequestPart("order") String orderJson,
            @Parameter(required = false, description = "수주 파일 수정요청")
            @RequestPart(value = "files", required = false) MultipartFile[] files) throws JsonProcessingException {


        ObjectMapper objectMapper = new ObjectMapper();
        RequestModifyOrder requestModifyOrder = objectMapper.readValue(orderJson, RequestModifyOrder.class);

        ResponseModifyOrder responseModifyOrder = orderService.modifyOrder(requestModifyOrder, files);

        return ResponseEntity.ok().body(responseModifyOrder);
    }

    @PostMapping("/delete")
    @Operation(summary = "수주 삭제요청", description = "수주 삭제요청을 등록합니다.")
    @ApiResponse(responseCode = "201", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력값 불일치")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseEntity<ResponseDeleteOrder> deleteOrder (
            @Parameter(required = true, description = "수주 삭제요청")
            @RequestBody RequestDeleteOrder requestDeleteOrder) {

        ResponseDeleteOrder responseDeleteOrder = orderService.deleteOrder(requestDeleteOrder);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDeleteOrder);
    }
}
