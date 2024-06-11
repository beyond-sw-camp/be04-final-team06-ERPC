package com.cineverse.erpc.order.note.controller;

import com.cineverse.erpc.order.note.dto.ResponseDeleteOrderNote;
import com.cineverse.erpc.order.note.dto.ResponseFindOrderNotesDTO;
import com.cineverse.erpc.order.note.dto.RequestRegistOrderNoteDTO;
import com.cineverse.erpc.order.note.dto.ResponseRegistOrderNoteDTO;
import com.cineverse.erpc.order.note.service.OrderNoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order_note")
public class OrderNoteController {

    private ModelMapper mapper;
    private OrderNoteService orderNoteService;

    @Autowired
    public OrderNoteController(ModelMapper mapper, OrderNoteService orderNoteService) {
        this.mapper = mapper;
        this.orderNoteService = orderNoteService;
    }

    @PostMapping("/regist")
    @Operation(summary = "수주 참고사항 등록", description = "수주 참고사항을 등록합니다.")
    @ApiResponse(responseCode = "201", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력값 불일치")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseEntity<ResponseRegistOrderNoteDTO> registOrderNote(
            @Parameter(required = true, description = "수주 참고사항 등록 요청")
            @RequestBody RequestRegistOrderNoteDTO registNote) {

        orderNoteService.registOrderNote(registNote);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseRegistOrderNoteDTO responseNote =
                mapper.map(registNote, ResponseRegistOrderNoteDTO.class);


        return ResponseEntity.status(HttpStatus.CREATED).body(responseNote);
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "수주 참고사항 조회", description = "수주 참고사항을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public List<ResponseFindOrderNotesDTO> findNotesByOrderId(
            @Parameter(required = true, description = "수주 고유번호")
            @PathVariable long orderId) {
        return orderNoteService.findNotesByOrderId(orderId);
    }

    @PatchMapping("/delete")
    @Operation(summary = "수주 참고사항 삭제", description = "수주 참고사항을 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseDeleteOrderNote deleteOrderNote(
            @Parameter(required = true, description = "수주 참고사항 고유번호")
            @RequestParam long orderNoteId) {
        return orderNoteService.deleteOrderNote(orderNoteId);
    }
}
