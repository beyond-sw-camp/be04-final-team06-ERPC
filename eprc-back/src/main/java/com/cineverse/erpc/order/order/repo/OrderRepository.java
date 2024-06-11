package com.cineverse.erpc.order.order.repo;

import com.cineverse.erpc.order.order.aggregate.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByOrderByOrderRegistrationId();

    List<Order> findByEmployeeEmployeeIdAndDepositDateIsNotNull(long employeeId);

    List<Order> findByEmployeeTeamCodeTeamCodeIdAndDepositDateIsNotNull(int teamCodeId);

    List<Order> findByDepositDateIsNotNull();

    Order findByTransaction_TransactionCode(String transactionCode);

    List<Order> findAllByOrderDeleteDateIsNull();
}
