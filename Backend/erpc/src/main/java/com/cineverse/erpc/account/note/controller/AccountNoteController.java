package com.cineverse.erpc.account.note.controller;

import com.cineverse.erpc.account.note.dto.RequestAccountNoteRegistDTO;
import com.cineverse.erpc.account.note.dto.ResponseDeleteAccountNote;
import com.cineverse.erpc.account.note.dto.ResponseFindAllAccountNotesDTO;
import com.cineverse.erpc.account.note.dto.ResponseAccountNoteRegistDTO;
import com.cineverse.erpc.account.note.service.AccountNoteService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account_note")
public class AccountNoteController {

    private ModelMapper modelMapper;
    private AccountNoteService accountNoteService;

    @Autowired
    public AccountNoteController(ModelMapper modelMapper, AccountNoteService accountNoteService) {
        this.modelMapper = modelMapper;
        this.accountNoteService = accountNoteService;
    }

    @PostMapping("/regist")
    public ResponseEntity<ResponseAccountNoteRegistDTO> registAccountNote(
            @RequestBody RequestAccountNoteRegistDTO requestAccountNote) {

        accountNoteService.registAccountNote(requestAccountNote);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseAccountNoteRegistDTO responseAccountNote =
                modelMapper.map(requestAccountNote, ResponseAccountNoteRegistDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseAccountNote);
    }

    @GetMapping("/{accountId}")
    public List<ResponseFindAllAccountNotesDTO> findAllAccountNotes(@PathVariable long accountId) {
        List<ResponseFindAllAccountNotesDTO> accountNotes =
                accountNoteService.findAllAccountNotes(accountId);

        return accountNotes;
    }

    @PatchMapping("/delete/{accountNoteId}")
    public ResponseDeleteAccountNote deleteAccountNote(@PathVariable long accountNoteId) {

        return accountNoteService.deleteAccountNote(accountNoteId);
    }
}
