package com.cineverse.erpc.account.note.service;

import com.cineverse.erpc.account.note.dto.RequestAccountNoteRegistDTO;
import com.cineverse.erpc.account.note.dto.ResponseAccountNoteRegistDTO;
import com.cineverse.erpc.account.note.dto.ResponseDeleteAccountNote;
import com.cineverse.erpc.account.note.dto.ResponseFindAllAccountNotesDTO;

import java.util.List;

public interface AccountNoteService {
    ResponseAccountNoteRegistDTO registAccountNote(RequestAccountNoteRegistDTO requestAccountNote);

    List<ResponseFindAllAccountNotesDTO> findAllAccountNotes(long accountId);

    ResponseDeleteAccountNote deleteAccountNote(long accountNoteId);
}
