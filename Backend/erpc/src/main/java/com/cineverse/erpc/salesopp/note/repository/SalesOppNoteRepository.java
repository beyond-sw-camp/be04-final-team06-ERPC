package com.cineverse.erpc.salesopp.note.repository;

import com.cineverse.erpc.salesopp.note.aggregate.entity.SalesOppNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOppNoteRepository extends JpaRepository<SalesOppNote, Long> {
}
