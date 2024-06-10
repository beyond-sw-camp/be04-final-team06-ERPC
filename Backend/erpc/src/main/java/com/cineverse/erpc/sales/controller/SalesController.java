package com.cineverse.erpc.sales.controller;

import com.cineverse.erpc.sales.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sales")
public class SalesController {

    private final SalesService salesService;

    @Autowired
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Map<String, Long>> getEmployeeSales(@PathVariable long employeeId) {
        Long sales = salesService.calculateIndividualSales(employeeId);
        Map<String, Long> response = new HashMap<>();
        response.put("employeeSales", sales);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/team/{teamCodeId}")
    public ResponseEntity<Map<String, Long>> getTeamSales(@PathVariable int teamCodeId) {
        Long sales = salesService.calculateTeamSales(teamCodeId);
        Map<String, Long> response = new HashMap<>();
        response.put("teamSales", sales);
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Long>> getTotalSales() {
        Long sales = salesService.calculateTotalSales();
        Map<String, Long> response = new HashMap<>();
        response.put("totalSales", sales);
        return ResponseEntity.ok(response);
    }
}
