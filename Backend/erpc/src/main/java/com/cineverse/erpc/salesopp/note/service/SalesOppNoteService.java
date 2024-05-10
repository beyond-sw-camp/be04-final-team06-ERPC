package com.cineverse.erpc.salesopp.note.service;

import com.cineverse.erpc.salesopp.note.dto.SalesOppNoteDTO;
import com.cineverse.erpc.salesopp.opportunity.aggregate.entity.SalesOpp;
import com.cineverse.erpc.salesopp.note.aggregate.entity.SalesOppNote;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDTO;

import java.util.List;

public interface SalesOppNoteService {
    void registSalesOppNote(SalesOppNoteDTO newSalesOppNote);

    SalesOppNote modifySalesOppNote(Long salesOppNoteId, SalesOppNoteDTO salesOppNote);

    SalesOppNote deleteSalesOppNote(Long salesOppNoteId);

    List<SalesOppNote> findsalesOppNoteList();
}
