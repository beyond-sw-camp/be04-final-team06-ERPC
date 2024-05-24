package com.cineverse.erpc.order.note.service;

import com.cineverse.erpc.order.note.aggregate.OrderNote;
import com.cineverse.erpc.order.note.dto.ResponseDeleteOrderNote;
import com.cineverse.erpc.order.note.dto.ResponseFindOrderNotesDTO;
import com.cineverse.erpc.order.note.dto.RequestRegistOrderNoteDTO;
import com.cineverse.erpc.order.note.dto.ResponseRegistOrderNoteDTO;
import com.cineverse.erpc.order.note.repo.OrderNoteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderNoteServiceImpl implements OrderNoteService{

    private ModelMapper mapper;
    private OrderNoteRepository orderNoteRepository;

    @Autowired
    public OrderNoteServiceImpl(ModelMapper mapper, OrderNoteRepository orderNoteRepository) {
        this.mapper = mapper;
        this.orderNoteRepository = orderNoteRepository;
    }

    @Override
    public ResponseRegistOrderNoteDTO registOrderNote(RequestRegistOrderNoteDTO registNote) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = dateFormat.format(date);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        OrderNote orderNote = mapper.map(registNote, OrderNote.class);

        orderNote.setOrderNoteDate(registDate);
        orderNoteRepository.save(orderNote);
        return mapper.map(orderNote, ResponseRegistOrderNoteDTO.class);
    }

    @Override
    public List<ResponseFindOrderNotesDTO> findNotesByOrderId(long orderId) {
        List<OrderNote> orderNotes = orderNoteRepository.findAllByOrderOrderRegistrationId(orderId);

        return orderNotes.stream().map(orderNote -> mapper
                .map(orderNote, ResponseFindOrderNotesDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDeleteOrderNote deleteOrderNote(long orderNoteId) {
        OrderNote orderNote = orderNoteRepository.findById(orderNoteId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 참고사항 입니다."));

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(date);

        orderNote.setOrderDeleteDate(currentDate);

        orderNoteRepository.save(orderNote);

        return mapper.map(orderNote, ResponseDeleteOrderNote.class);
    }
}
