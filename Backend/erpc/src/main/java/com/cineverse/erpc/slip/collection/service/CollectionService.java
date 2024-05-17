package com.cineverse.erpc.slip.collection.service;

import com.cineverse.erpc.slip.collection.aggregate.Collection;

import java.util.List;

public interface CollectionService {
    List<Collection> findCollectionList();
}
