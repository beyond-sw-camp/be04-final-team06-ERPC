package com.cineverse.erpc.shipment.controller;

import com.cineverse.erpc.shipment.dto.RequestModifyShipment;
import com.cineverse.erpc.shipment.dto.ShipmentDTO;
import com.cineverse.erpc.shipment.service.ShipmentService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {
    private ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PatchMapping("/modify")
    private ResponseEntity<ShipmentDTO> modifyShipment(
            @RequestBody RequestModifyShipment modifyShipment) {

        ShipmentDTO shipmentDTO = shipmentService.modifyShipment(modifyShipment);

        return ResponseEntity.status(HttpStatus.OK).body(shipmentDTO);
    }
}
