package com.cineverse.erpc.target.service;

import com.cineverse.erpc.target.aggregate.Target;
import com.cineverse.erpc.target.repository.TargetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetServiceImpl implements TargetService{
    private final ModelMapper modelMapper;
    private final TargetRepository targetRepository;

    @Autowired
    public TargetServiceImpl(ModelMapper modelMapper, TargetRepository targetRepository) {
        this.modelMapper = modelMapper;
        this.targetRepository = targetRepository;
    }

    @Override
    public List<Target> findTotalTarget() {
        return List.of();
    }
}
