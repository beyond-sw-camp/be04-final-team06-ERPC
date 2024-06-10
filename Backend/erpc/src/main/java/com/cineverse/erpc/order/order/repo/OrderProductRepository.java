package com.cineverse.erpc.order.order.repo;

import com.cineverse.erpc.order.order.aggregate.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    void deleteAllByOrderOrderRegistrationId(long orderId);
}
