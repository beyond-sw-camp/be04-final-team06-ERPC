package com.cineverse.erpc.order.note.service;

import com.cineverse.erpc.order.note.dto.ResponseDeleteOrderNote;
import com.cineverse.erpc.order.note.dto.ResponseFindOrderNotesDTO;
import com.cineverse.erpc.order.note.dto.RequestRegistOrderNoteDTO;
import com.cineverse.erpc.order.note.dto.ResponseRegistOrderNoteDTO;

import java.util.List;

public interface OrderNoteService {
    ResponseRegistOrderNoteDTO registOrderNote(RequestRegistOrderNoteDTO registNote);

    List<ResponseFindOrderNotesDTO> findNotesByOrderId(long orderId);

    ResponseDeleteOrderNote deleteOrderNote(long orderNoteId);
}
