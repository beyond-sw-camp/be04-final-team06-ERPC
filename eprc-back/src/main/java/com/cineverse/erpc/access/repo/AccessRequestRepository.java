package com.cineverse.erpc.access.repo;

import com.cineverse.erpc.access.aggregate.AccessRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccessRequestRepository extends JpaRepository<AccessRequest, Long> {
    List<AccessRequest> findAllByAccessRequestStatus(String accessRequestStatus);
}
