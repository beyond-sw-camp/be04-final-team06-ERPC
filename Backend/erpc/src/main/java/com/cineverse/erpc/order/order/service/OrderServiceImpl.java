package com.cineverse.erpc.order.order.service;

import com.cineverse.erpc.contract.aggregate.ContractCategory;
import com.cineverse.erpc.order.order.aggregate.Order;
import com.cineverse.erpc.order.order.aggregate.OrderProduct;
import com.cineverse.erpc.order.order.aggregate.ShipmentStatus;
import com.cineverse.erpc.order.order.dto.OrderDTO;
import com.cineverse.erpc.order.order.dto.RequestRegistOrderDTO;
import com.cineverse.erpc.order.order.dto.ResponseOrderLists;
import com.cineverse.erpc.order.order.repo.OrderProductRepository;
import com.cineverse.erpc.order.order.repo.OrderRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private ModelMapper mapper;
    private OrderRepository orderRepository;
    private OrderProductRepository orderProductRepository;

    @Autowired
    public OrderServiceImpl(ModelMapper mapper,
                            OrderRepository orderRepository,
                            OrderProductRepository orderProductRepository) {
        this.mapper = mapper;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    @Transactional
    public void registOrder(RequestRegistOrderDTO requestOrder) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(date);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Order order = mapper.map(requestOrder, Order.class);

        ShipmentStatus shipmentStatus = new ShipmentStatus();
        shipmentStatus.setShipmentStatusId(1);
        order.setOrderDate(currentDate);
        order.setShipmentStatus(shipmentStatus);

        orderRepository.save(order);

        for (OrderProduct product : requestOrder.getOrderProduct()) {
            OrderProduct orderProduct = registOrderProduct(product, order);
        }
    }

    private OrderProduct registOrderProduct(OrderProduct product, Order order) {
        product.setOrder(order);
        orderProductRepository.save(product);

        return product;
    }

    @Override
    public OrderDTO findOrderById(long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 수주입니다."));

        OrderDTO orderDTO = mapper.map(order, OrderDTO.class);

        return orderDTO;
    }

    @Override
    public List<ResponseOrderLists> findAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(order -> mapper
                .map(order, ResponseOrderLists.class))
                .collect(Collectors.toList());
    }
}
