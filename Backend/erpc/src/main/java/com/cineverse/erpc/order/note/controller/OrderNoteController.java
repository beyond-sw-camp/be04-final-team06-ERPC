package com.cineverse.erpc.order.note.controller;

import com.cineverse.erpc.order.note.dto.ResponseDeleteOrderNote;
import com.cineverse.erpc.order.note.dto.ResponseFindOrderNotesDTO;
import com.cineverse.erpc.order.note.dto.RequestRegistOrderNoteDTO;
import com.cineverse.erpc.order.note.dto.ResponseRegistOrderNoteDTO;
import com.cineverse.erpc.order.note.service.OrderNoteService;
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
    public ResponseEntity<ResponseRegistOrderNoteDTO> registOrderNote(
            @RequestBody RequestRegistOrderNoteDTO registNote) {

        orderNoteService.registOrderNote(registNote);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseRegistOrderNoteDTO responseNote =
                mapper.map(registNote, ResponseRegistOrderNoteDTO.class);


        return ResponseEntity.status(HttpStatus.CREATED).body(responseNote);
    }

    @GetMapping("/{orderId}")
    public List<ResponseFindOrderNotesDTO> findNotesByOrderId(@PathVariable long orderId) {
        return orderNoteService.findNotesByOrderId(orderId);
    }

    @PatchMapping("/delete/{orderNoteId}")
    public ResponseDeleteOrderNote deleteOrderNote(@PathVariable long orderNoteId) {
        return orderNoteService.deleteOrderNote(orderNoteId);
    }
}
