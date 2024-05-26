package com.cineverse.erpc.order.note;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.repo.EmployeeRepository;
import com.cineverse.erpc.order.note.aggregate.OrderNote;
import com.cineverse.erpc.order.note.dto.RequestRegistOrderNoteDTO;
import com.cineverse.erpc.order.note.dto.ResponseDeleteOrderNote;
import com.cineverse.erpc.order.note.dto.ResponseFindOrderNotesDTO;
import com.cineverse.erpc.order.note.dto.ResponseRegistOrderNoteDTO;
import com.cineverse.erpc.order.note.repo.OrderNoteRepository;
import com.cineverse.erpc.order.note.service.OrderNoteService;
import com.cineverse.erpc.order.order.aggregate.Order;
import com.cineverse.erpc.order.order.repo.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderNoteTests {
    private final OrderNoteService orderNoteService;
    private final EmployeeRepository employeeRepository;
    private final OrderRepository orderRepository;
    private final OrderNoteRepository orderNoteRepository;

    @Autowired
    public OrderNoteTests(OrderNoteService orderNoteService,
                          EmployeeRepository employeeRepository,
                          OrderRepository orderRepository,
                          OrderNoteRepository orderNoteRepository) {
        this.orderNoteService = orderNoteService;
        this.employeeRepository = employeeRepository;
        this.orderRepository = orderRepository;
        this.orderNoteRepository = orderNoteRepository;
    }

    @Test
    @Transactional
    @DisplayName("수주 참고사항 등록 테스트")
    public void successRegistOrderNoteTest() {
        Employee employee = employeeRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다."));

        Order order = orderRepository.findById(Long.valueOf(2))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 수주입니다."));

        RequestRegistOrderNoteDTO orderNote = RequestRegistOrderNoteDTO.builder()
                .order(order)
                .employee(employee)
                .orderNote("수주 참고사항 내용")
                .build();

        ResponseRegistOrderNoteDTO testOrderNote = orderNoteService.registOrderNote(orderNote);

        assertThat(orderNote.getOrderNote()).isEqualTo(testOrderNote.getOrderNote());
        assertThat(orderNote.getOrder()).isEqualTo(testOrderNote.getOrder());
        assertThat(orderNote.getEmployee()).isEqualTo(testOrderNote.getEmployee());
        assertThat(testOrderNote.getOrderNoteDate()).isNotNull();
        assertThat(testOrderNote.getOrderDeleteDate()).isNull();
    }

    @Test
    @Transactional
    @DisplayName("수주 참고사항 전체조회 성공 테스트")
    public void successFindOrderNotesTest() {
        List<ResponseFindOrderNotesDTO> orderNotes = orderNoteService.findNotesByOrderId(Long.valueOf(2));

        assertThat(orderNotes).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("수주 참고사항 삭제 성공 테스트")
    public void successDeleteOrderNoteTest() {
        OrderNote orderNote = orderNoteRepository.findById(Long.valueOf(2))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 참고사항 입니다."));

        assertThat(orderNote.getOrderDeleteDate()).isNull();

        ResponseDeleteOrderNote testOrderNote = orderNoteService.deleteOrderNote(Long.valueOf(2));

        assertThat(testOrderNote.getOrderDeleteDate()).isNotNull();

    }
}
