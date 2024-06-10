package com.cineverse.erpc.quotation.note.repo;

import com.cineverse.erpc.quotation.note.aggregate.QuotationNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuotationNoteRepository extends JpaRepository<QuotationNote, Long> {
    List<QuotationNote> findByQuotationQuotationId(long quotationId);
}
