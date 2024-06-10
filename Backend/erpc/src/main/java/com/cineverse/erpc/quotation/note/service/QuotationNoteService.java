package com.cineverse.erpc.quotation.note.service;

import com.cineverse.erpc.quotation.note.dto.RequestRegistQuotationNoteDTO;
import com.cineverse.erpc.quotation.note.dto.ResponseFindAllQuotationNotesDTO;

import java.util.List;

public interface QuotationNoteService {
    void registQuotationNote(RequestRegistQuotationNoteDTO requestQuotationNote);

    List<ResponseFindAllQuotationNotesDTO> findAllQuotationNotes(long quotationId);
}
