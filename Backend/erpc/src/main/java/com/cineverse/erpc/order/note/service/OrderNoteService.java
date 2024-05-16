package com.cineverse.erpc.order.note.service;

import com.cineverse.erpc.order.note.dto.ResponseFindOrderNotesDTO;
import com.cineverse.erpc.order.note.dto.RequestRegistOrderNoteDTO;

import java.util.List;

public interface OrderNoteService {
    void registOrderNote(RequestRegistOrderNoteDTO registNote);

    List<ResponseFindOrderNotesDTO> findNotesByOrderId(long orderId);
}
