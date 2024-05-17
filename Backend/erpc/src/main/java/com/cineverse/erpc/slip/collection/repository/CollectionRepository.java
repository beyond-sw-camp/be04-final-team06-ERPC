package com.cineverse.erpc.slip.collection.repository;

import com.cineverse.erpc.slip.collection.aggregate.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
}
