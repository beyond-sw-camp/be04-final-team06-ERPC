package com.cineverse.erpc.warehouse.service;

import com.cineverse.erpc.warehouse.aggregate.Warehouse;
import com.cineverse.erpc.warehouse.repository.WarehouseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService{

    private final ModelMapper modelMapper;
    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseServiceImpl(ModelMapper modelMapper, WarehouseRepository warehouseRepository) {
        this.modelMapper = modelMapper;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public List<Warehouse> findWarehouseList() {
        List<Warehouse> warehouseList = warehouseRepository.findAll();

        return warehouseList.stream().map(warehouse -> modelMapper
                        .map(warehouse, Warehouse.class))
                .collect(Collectors.toList());
    }
}
