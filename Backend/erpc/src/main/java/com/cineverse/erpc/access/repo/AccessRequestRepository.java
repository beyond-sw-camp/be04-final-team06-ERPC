package com.cineverse.erpc.access.repo;

import com.cineverse.erpc.access.aggregate.AccessRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessRequestRepository extends JpaRepository<AccessRequest, Long> {
}
