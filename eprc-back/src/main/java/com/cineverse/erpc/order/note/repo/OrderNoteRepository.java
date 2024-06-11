package com.cineverse.erpc.order.note.repo;

import com.cineverse.erpc.order.note.aggregate.OrderNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderNoteRepository extends JpaRepository<OrderNote, Long> {
    List<OrderNote> findAllByOrderOrderRegistrationId(long orderId);
}
