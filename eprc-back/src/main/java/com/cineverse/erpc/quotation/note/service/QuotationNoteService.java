package com.cineverse.erpc.quotation.note.service;

import com.cineverse.erpc.quotation.note.dto.RequestRegistQuotationNoteDTO;
import com.cineverse.erpc.quotation.note.dto.ResponseDeleteQuotationNote;
import com.cineverse.erpc.quotation.note.dto.ResponseFindAllQuotationNotesDTO;
import com.cineverse.erpc.quotation.note.dto.ResponseRegistQuotationNoteDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuotationNoteService {
    ResponseRegistQuotationNoteDTO registQuotationNote(RequestRegistQuotationNoteDTO requestQuotationNote);

    List<ResponseFindAllQuotationNotesDTO> findAllQuotationNotes(long quotationId);

    ResponseDeleteQuotationNote deleteQuotationNote(long quotationNoteId);
}
