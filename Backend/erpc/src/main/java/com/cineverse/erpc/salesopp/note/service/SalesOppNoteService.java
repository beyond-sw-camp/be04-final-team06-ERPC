package com.cineverse.erpc.salesopp.note.service;

import com.cineverse.erpc.salesopp.note.dto.SalesOppNoteDTO;
import com.cineverse.erpc.salesopp.note.aggregate.SalesOppNote;

import java.util.List;

public interface SalesOppNoteService {

    SalesOppNote registSalesOppNote(SalesOppNoteDTO oppNote);

    SalesOppNote modifySalesOppNote(Long salesOppNoteId, SalesOppNoteDTO oppNote);

    SalesOppNote deleteSalesOppNote(Long salesOppNoteId);

    List<SalesOppNote> findsalesOppNoteList();
}
