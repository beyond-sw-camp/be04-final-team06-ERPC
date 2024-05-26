package com.cineverse.erpc.order.order.repo;

import com.cineverse.erpc.order.order.aggregate.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByEmployeeEmployeeIdAndDownPaymentIsNotNull(long employeeId);

    List<Order> findByEmployeeTeamCodeTeamCodeIdAndDownPaymentIsNotNull(int teamCodeId);

    List<Order> findByDownPaymentIsNotNull();

    Order findByTransaction_TransactionCode(String transactionCode);
}
