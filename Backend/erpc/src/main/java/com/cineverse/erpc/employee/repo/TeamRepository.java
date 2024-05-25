package com.cineverse.erpc.employee.repo;

import com.cineverse.erpc.employee.aggregate.TeamCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamCode, Integer> {
}
