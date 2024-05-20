package com.cineverse.erpc.admin.delete.service;

import com.cineverse.erpc.admin.delete.aggregate.SalesOppDelete;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteServiceImpl implements DeleteService {
    @Override
    public List<SalesOppDelete> findSalesOppDeleteRequestList() {
        return null;
    }
}
