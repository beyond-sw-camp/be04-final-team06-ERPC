package com.cineverse.erpc.order.note.aggregate;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.order.order.aggregate.Order;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_order_note")
public class OrderNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_note_id")
    private long orderNoteId;

    @Column(name = "order_note")
    private String orderNote;

    @Column(name = "order_note_date")
    private String orderNoteDate;

    @Column(name = "order_delete_date")
    private String orderDeleteDate;

    @JoinColumn(name = "order_registration_id")
    @ManyToOne
    private Order order;

    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;
}
