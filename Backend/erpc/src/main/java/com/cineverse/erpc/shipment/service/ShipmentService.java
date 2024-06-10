package com.cineverse.erpc.shipment.service;

import com.cineverse.erpc.order.order.aggregate.Order;
import com.cineverse.erpc.order.order.repo.OrderRepository;
import com.cineverse.erpc.shipment.aggregate.Shipment;
import com.cineverse.erpc.shipment.dto.RequestModifyShipment;
import com.cineverse.erpc.shipment.dto.ShipmentDTO;
import com.cineverse.erpc.shipment.repo.ShipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipmentService {

    private ModelMapper mapper;
    private ShipmentRepository shipmentRepository;
    private OrderRepository orderRepository;

    @Autowired
    public ShipmentService(ModelMapper mapper,
                           ShipmentRepository shipmentRepository,
                           OrderRepository orderRepository) {
        this.mapper = mapper;
        this.shipmentRepository = shipmentRepository;
        this.orderRepository = orderRepository;
    }

    public ShipmentDTO modifyShipment(RequestModifyShipment modifyShipment) {
        Shipment shipment = shipmentRepository.findById(modifyShipment.getShipmentId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 출하입니다."));

        Order order = orderRepository.findByTransaction_TransactionCode(modifyShipment.getTransactionCode());

        if (modifyShipment.getOrderDueDate() != null) {
            shipment.setOrderDueDate(modifyShipment.getOrderDueDate());
            order.setOrderDueDate(modifyShipment.getOrderDueDate());
        }

        if (modifyShipment.getEstimatedDeliveryDate() != null) {
            shipment.setEstimatedDeliveryDate(modifyShipment.getEstimatedDeliveryDate());
            order.setEstimatedDeliveryDate(modifyShipment.getEstimatedDeliveryDate());
        }

        if (modifyShipment.getEstimatedArriveDate() != null) {
            shipment.setEstimatedArriveDate(modifyShipment.getEstimatedArriveDate());
            order.setEstimatedArriveDate(modifyShipment.getEstimatedArriveDate());
        }

        if (modifyShipment.getArriveDate() != null) {
            shipment.setArriveDate(modifyShipment.getArriveDate());
            order.setArriveDate(modifyShipment.getArriveDate());
        }

        if (modifyShipment.getEstimatedReleaseDate() != null) {
            shipment.setEstimatedReleaseDate(modifyShipment.getEstimatedReleaseDate());
            order.setEstimatedReleaseDate(modifyShipment.getEstimatedReleaseDate());
        }

        if (modifyShipment.getReleaseDate() != null) {
            shipment.setReleaseDate(modifyShipment.getReleaseDate());
            order.setReleaseDate(modifyShipment.getReleaseDate());
        }

        if (modifyShipment.getShipmentStatus() != null) {
            shipment.setShipmentStatus(modifyShipment.getShipmentStatus());
            order.setShipmentStatus(modifyShipment.getShipmentStatus());
        }

        shipmentRepository.save(shipment);
        orderRepository.save(order);

        return mapper.map(shipment, ShipmentDTO.class);
    }
}
