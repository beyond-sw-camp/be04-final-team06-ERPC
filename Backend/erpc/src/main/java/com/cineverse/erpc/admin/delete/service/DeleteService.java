package com.cineverse.erpc.admin.delete.service;

import com.cineverse.erpc.admin.delete.aggregate.SalesOppDelete;

import java.util.List;

public interface DeleteService {
    List<SalesOppDelete> findSalesOppDeleteRequestList();
}
