package com.cineverse.erpc.order.order.service;

import com.cineverse.erpc.file.service.FileUploadService;
import com.cineverse.erpc.order.order.aggregate.Order;
import com.cineverse.erpc.order.order.aggregate.OrderDeleteRequest;
import com.cineverse.erpc.order.order.aggregate.OrderProduct;
import com.cineverse.erpc.order.order.aggregate.ShipmentStatus;
import com.cineverse.erpc.order.order.dto.*;
import com.cineverse.erpc.order.order.repo.OrderDeleteRequestRepository;
import com.cineverse.erpc.order.order.repo.OrderProductRepository;
import com.cineverse.erpc.order.order.repo.OrderRepository;
import com.cineverse.erpc.quotation.quotation.aggregate.Transaction;
import com.cineverse.erpc.quotation.quotation.repo.TransactionRepository;
import com.cineverse.erpc.shipment.aggregate.Shipment;
import com.cineverse.erpc.shipment.repo.ShipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final ModelMapper mapper;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final FileUploadService fileUploadService;
    private final OrderDeleteRequestRepository orderDeleteRequestRepository;
    private final ShipmentRepository shipmentRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public OrderServiceImpl(ModelMapper mapper,
                            OrderRepository orderRepository,
                            FileUploadService fileUploadService,
                            OrderProductRepository orderProductRepository,
                            OrderDeleteRequestRepository orderDeleteRequestRepository,
                            ShipmentRepository shipmentRepository,
                            TransactionRepository transactionRepository) {
      
        this.mapper = mapper;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.orderDeleteRequestRepository = orderDeleteRequestRepository;
        this.shipmentRepository = shipmentRepository;
        this.transactionRepository = transactionRepository;
        this.fileUploadService = fileUploadService;
    }

    @Override
    @Transactional
    public ResponseRegistOrderDTO registOrder(RequestRegistOrderDTO requestOrder, MultipartFile[] files) {
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

        Transaction transaction = transactionRepository.findById(order.getTransaction().getTransactionId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 거래코드 입니다."));

        Shipment shipment = new Shipment();
        shipment.setOrderDueDate(order.getOrderDueDate());
        shipment.setTransactionCode(transaction.getTransactionCode());
        shipment.setShipmentStatus(shipmentStatus);
        shipmentRepository.save(shipment);


        for (OrderProduct product : requestOrder.getOrderProduct()) {
            OrderProduct orderProduct = registOrderProduct(product, order);
        }

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String url = fileUploadService.saveOrderFile(file, order);
            }
        }

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(order, ResponseRegistOrderDTO.class);
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

    @Override
    @Transactional
    public ResponseModifyOrder modifyOrder(long orderId, RequestModifyOrder requestModifyOrder, MultipartFile[] files) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 수주 입니다."));

        if(!requestModifyOrder.getOrderProduct().isEmpty()) {
            orderProductRepository.deleteAllByOrderOrderRegistrationId(orderId);
            for (OrderProduct product : requestModifyOrder.getOrderProduct()) {
                OrderProduct orderProduct = modifyOrderProduct(order, product);
            }
        }

        if (requestModifyOrder.getContactDate() != null) {
            order.setContactDate(requestModifyOrder.getContactDate());
        }

        if (requestModifyOrder.getEstimatedDeliveryDate() != null) {
            order.setEstimatedDeliveryDate(requestModifyOrder.getEstimatedDeliveryDate());
        }

        if (requestModifyOrder.getOrderTotalPrice() != 0) {
            order.setOrderTotalPrice(requestModifyOrder.getOrderTotalPrice());
        }

        if (requestModifyOrder.getOrderDueDate() != null) {
            order.setOrderDueDate(requestModifyOrder.getOrderDueDate());
        }

        if (requestModifyOrder.getEstimatedArriveDate() != null) {
            order.setEstimatedArriveDate(requestModifyOrder.getEstimatedArriveDate());
        }

        if (requestModifyOrder.getTotalBalance() != 0) {
            order.setTotalBalance(requestModifyOrder.getTotalBalance());
        }

        if (requestModifyOrder.getDownPayment() != 0) {
            order.setDownPayment(requestModifyOrder.getDownPayment());
        }

        if (requestModifyOrder.getProgressPayment() != 0) {
            order.setProgressPayment(requestModifyOrder.getProgressPayment());
        }

        if (requestModifyOrder.getBalance() != 0) {
            order.setBalance(requestModifyOrder.getBalance());
        }

        if (requestModifyOrder.getArriveDate() != null) {
            order.setArriveDate(requestModifyOrder.getArriveDate());
        }

        if (requestModifyOrder.getReleaseDate() != null) {
            order.setReleaseDate(requestModifyOrder.getReleaseDate());
        }

        if (requestModifyOrder.getEstimatedReleaseDate() != null) {
            order.setEstimatedReleaseDate(requestModifyOrder.getEstimatedReleaseDate());
        }

        if (requestModifyOrder.getOrderNote() != null) {
            order.setOrderNote(requestModifyOrder.getOrderNote());
        }

        if (requestModifyOrder.getDepositDate() != null) {
            order.setDepositDate(requestModifyOrder.getDepositDate());
        }

        if (requestModifyOrder.getAccount() != null) {
            order.setAccount(requestModifyOrder.getAccount());
        }

        if (requestModifyOrder.getEmployee() != null) {
            order.setEmployee(requestModifyOrder.getEmployee());
        }

        if (requestModifyOrder.getTransaction() != null) {
            order.setTransaction(requestModifyOrder.getTransaction());
        }

        if (requestModifyOrder.getWarehouse() != null) {
            order.setWarehouse(requestModifyOrder.getWarehouse());
        }

        if (requestModifyOrder.getShipmentStatus() != null) {
            order.setShipmentStatus(requestModifyOrder.getShipmentStatus());
        }

        if (requestModifyOrder.getContractCategory() != null) {
            order.setContractCategory(requestModifyOrder.getContractCategory());
        }

        if (files != null && files.length > 0) {
            fileUploadService.deleteFilesByOrder(order);

            for (MultipartFile file : files) {
                fileUploadService.saveOrderFile(file, order);
            }
        }

        orderRepository.save(order);

        ResponseModifyOrder modifyOrder = mapper.map(order, ResponseModifyOrder.class);

        return modifyOrder;
    }

    private OrderProduct modifyOrderProduct(Order order, OrderProduct product) {
        product.setOrder(order);
        OrderProduct orderProduct = orderProductRepository.save(product);

        return orderProduct;
    }

    @Override
    public ResponseDeleteOrder deleteOrder(RequestDeleteOrder requestDeleteOrder) {
        OrderDeleteRequest orderDeleteRequest = new OrderDeleteRequest();
        orderDeleteRequest.setOrder(requestDeleteOrder.getOrder());
        orderDeleteRequest.setOrderDeleteRequestReason(requestDeleteOrder.getOrderDeleteRequestReason());
        orderDeleteRequest.setOrderDeleteRequestStatus("N");

        orderDeleteRequestRepository.save(orderDeleteRequest);

        return mapper.map(orderDeleteRequest, ResponseDeleteOrder.class);
    }
}
