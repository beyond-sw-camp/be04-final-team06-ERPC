package com.cineverse.erpc.shipment.controller;

import com.cineverse.erpc.shipment.dto.RequestModifyShipment;
import com.cineverse.erpc.shipment.dto.ShipmentDTO;
import com.cineverse.erpc.shipment.service.ShipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "출하 수정", description = "출하 정보를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력값 불일치")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    private ResponseEntity<ShipmentDTO> modifyShipment(
            @Parameter(required = true, description = "출하 수정요청")
            @RequestBody RequestModifyShipment modifyShipment) {

        ShipmentDTO shipmentDTO = shipmentService.modifyShipment(modifyShipment);

        return ResponseEntity.status(HttpStatus.OK).body(shipmentDTO);
    }
}
