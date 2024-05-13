package com.cineverse.erpc.contract.controller;

import com.cineverse.erpc.contract.service.ContractService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contract")
public class ContractController {

    private final ContractService contractService;
    private final ModelMapper modelMapper;

    @Autowired
    public ContractController(ContractService contractService, ModelMapper modelMapper) {
        this.contractService = contractService;
        this.modelMapper = modelMapper;
    }


}
