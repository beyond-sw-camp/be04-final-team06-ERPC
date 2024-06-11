package com.cineverse.erpc.account.note.repo;

import com.cineverse.erpc.account.note.aggregate.AccountNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountNoteRepository extends JpaRepository<AccountNote, Long> {

    List<AccountNote> findByAccountAccountId(long accountId);
}
