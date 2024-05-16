package com.cineverse.erpc.target.repository;

import com.cineverse.erpc.target.aggregate.Target;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetRepository extends JpaRepository<Target, Long> {
}
