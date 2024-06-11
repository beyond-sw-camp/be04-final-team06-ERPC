package com.cineverse.erpc.health.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealchController {

    @GetMapping("/")
    public String healthCheck() {
        return "Service is up and running...";
    }
}
