package com.cineverse.erpc.account.note.service;

import com.cineverse.erpc.account.note.aggregate.AccountNote;
import com.cineverse.erpc.account.note.dto.RequestAccountNoteRegistDTO;
import com.cineverse.erpc.account.note.dto.ResponseAccountNoteRegistDTO;
import com.cineverse.erpc.account.note.dto.ResponseFindAllAccountNotesDTO;
import com.cineverse.erpc.account.note.repo.AccountNoteRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountNoteServiceImpl implements AccountNoteService{

    private AccountNoteRepository accountNoteRepository;
    private ModelMapper modelMapper;

    @Autowired
    public AccountNoteServiceImpl(AccountNoteRepository accountNoteRepository, ModelMapper modelMapper) {
        this.accountNoteRepository = accountNoteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseAccountNoteRegistDTO registAccountNote(RequestAccountNoteRegistDTO requestAccountNote) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        AccountNote accountNote = modelMapper.map(requestAccountNote, AccountNote.class);

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = format.format(date);
        accountNote.setAccountNoteDate(currentDate);

        accountNoteRepository.save(accountNote);

        return modelMapper.map(accountNote, ResponseAccountNoteRegistDTO.class);
    }

    @Override
    public List<ResponseFindAllAccountNotesDTO> findAllAccountNotes(long accountId) {
        List<AccountNote> accountNotes = accountNoteRepository.findByAccountAccountId(accountId);

        return accountNotes.stream().map(accountNote -> modelMapper.
                map(accountNote, ResponseFindAllAccountNotesDTO.class)).collect(Collectors.toList());
    }
}
