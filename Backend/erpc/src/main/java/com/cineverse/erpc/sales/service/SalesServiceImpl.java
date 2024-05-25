package com.cineverse.erpc.sales.service;

import com.cineverse.erpc.order.order.aggregate.Order;
import com.cineverse.erpc.order.order.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {

    private final OrderRepository orderRepository;

    @Autowired
    public SalesServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Long calculateIndividualSales(long employeeId) {
        List<Order> orders = orderRepository.findByEmployeeEmployeeIdAndDownPaymentIsNotNull(employeeId);
        return orders.stream().mapToLong(Order::getOrderTotalPrice).sum();
    }

    @Override
    public Long calculateTeamSales(int teamCodeId) {
        List<Order> orders = orderRepository.findByEmployeeTeamCodeTeamCodeIdAndDownPaymentIsNotNull(teamCodeId);
        return orders.stream().mapToLong(Order::getOrderTotalPrice).sum();
    }

    @Override
    public Long calculateTotalSales() {
        List<Order> orders = orderRepository.findByDownPaymentIsNotNull();
        return orders.stream().mapToLong(Order::getOrderTotalPrice).sum();
    }
}
