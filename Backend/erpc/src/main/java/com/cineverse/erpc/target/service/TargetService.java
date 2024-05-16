package com.cineverse.erpc.target.service;

import com.cineverse.erpc.target.aggregate.Target;

import java.util.List;

public interface TargetService {
    List<Target> findTotalTarget();
}
