package com.cineverse.erpc.sales.service;

import com.cineverse.erpc.order.order.aggregate.Order;
import com.cineverse.erpc.order.order.repo.OrderRepository;
import com.cineverse.erpc.sales.dto.SalesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalesServiceImpl implements SalesService {

    private final OrderRepository orderRepository;

    private static final int START_YEAR = 2022;
    private static final int END_YEAR = 2024;

    @Autowired
    public SalesServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Long calculateIndividualSales(long employeeId) {
        List<Order> orders = orderRepository.findByEmployeeEmployeeIdAndDepositDateIsNotNull(employeeId);
        return orders.stream().mapToLong(Order::getOrderTotalPrice).sum();
    }

    @Override
    public Long calculateTeamSales(int teamCodeId) {
        List<Order> orders = orderRepository.findByEmployeeTeamCodeTeamCodeIdAndDepositDateIsNotNull(teamCodeId);
        return orders.stream().mapToLong(Order::getOrderTotalPrice).sum();
    }

    @Override
    public Long calculateTotalSales() {
        List<Order> orders = orderRepository.findByDepositDateIsNotNull();
        return orders.stream().mapToLong(Order::getOrderTotalPrice).sum();
    }

    @Override
    public Long calculateTeamMonthlySales(int teamCodeId, int year, int month) {
        List<Order> orders = orderRepository.findByEmployeeTeamCodeTeamCodeIdAndDepositDateIsNotNull(teamCodeId);
        return orders.stream()
                .filter(order -> {
                    String orderDate = order.getOrderDate(); // Assuming format is "yyyy-MM-dd"
                    int orderYear = Integer.parseInt(orderDate.substring(0, 4));
                    int orderMonth = Integer.parseInt(orderDate.substring(5, 7));
                    return orderYear == year && orderMonth == month;
                })
                .mapToLong(Order::getOrderTotalPrice)
                .sum();
    }

    @Override
    public SalesDTO calculateTeamYearlySales(int teamCodeId) {
        List<Order> orders = orderRepository.findByEmployeeTeamCodeTeamCodeIdAndDepositDateIsNotNull(teamCodeId);

        return new SalesDTO(calculateYearlySales(orders));
    }

    @Override
    public SalesDTO calculateEmployeeYearlySales(long employeeId) {
        List<Order> orders = orderRepository.findByEmployeeEmployeeIdAndDepositDateIsNotNull(employeeId);

        return new SalesDTO(calculateYearlySales(orders));
    }

    @Override
    public SalesDTO calculateTotalYearlySales() {
        List<Order> orders = orderRepository.findByDepositDateIsNotNull();

        return new SalesDTO(calculateYearlySales(orders));
    }

    private Map<Integer, Map<String, Long>> calculateYearlySales(List<Order> orders) {
        Map<Integer, Map<String, Long>> yearlySales = new LinkedHashMap<>();

        for (int year = START_YEAR; year <= END_YEAR; year++) {
            Map<String, Long> monthsMap = new LinkedHashMap<>();
            for (int i = 1; i <= 12; i++) {
                monthsMap.put(String.format("%02d", i), 0L);
            }
            monthsMap.put("total", 0L);
            yearlySales.put(year, monthsMap);
        }

        orders.forEach(order -> {
            String orderDate = order.getOrderDate();
            int orderYear = Integer.parseInt(orderDate.substring(0, 4));
            int orderMonth = Integer.parseInt(orderDate.substring(5, 7));
            long orderTotalPrice = order.getOrderTotalPrice();

            if (yearlySales.containsKey(orderYear)) {
                yearlySales.get(orderYear).merge(String.format("%02d", orderMonth), orderTotalPrice, Long::sum);
                yearlySales.get(orderYear).merge("total", orderTotalPrice, Long::sum);
            }
        });

        return yearlySales;
    }
}
