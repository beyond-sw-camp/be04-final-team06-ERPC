package com.cineverse.erpc.salesopp.note.repository;

import com.cineverse.erpc.salesopp.note.aggregate.SalesOppNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesOppNoteRepository extends JpaRepository<SalesOppNote, Long> {
    List<SalesOppNote> findBySalesOppNoteDeleteDateIsNullOrderBySalesOppNoteIdDesc();
}
