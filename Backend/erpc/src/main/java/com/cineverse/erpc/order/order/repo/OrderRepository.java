package com.cineverse.erpc.order.order.repo;

import com.cineverse.erpc.order.order.aggregate.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
