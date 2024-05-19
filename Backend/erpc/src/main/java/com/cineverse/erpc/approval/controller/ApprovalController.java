package com.cineverse.erpc.approval.controller;

import com.cineverse.erpc.approval.service.ApprovalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApprovalController {
    private ModelMapper mapper;
    private ApprovalService approvalService;

    @Autowired
    public ApprovalController(ModelMapper mapper, ApprovalService approvalService) {
        this.mapper = mapper;
        this.approvalService = approvalService;
    }
}
