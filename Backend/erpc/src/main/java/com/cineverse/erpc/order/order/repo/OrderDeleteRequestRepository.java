package com.cineverse.erpc.order.order.repo;

import com.cineverse.erpc.order.order.aggregate.OrderDeleteRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDeleteRequestRepository extends JpaRepository<OrderDeleteRequest, Long> {
}
