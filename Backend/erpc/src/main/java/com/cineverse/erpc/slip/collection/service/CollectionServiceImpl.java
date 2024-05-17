package com.cineverse.erpc.slip.collection.service;

import com.cineverse.erpc.slip.collection.aggregate.Collection;
import com.cineverse.erpc.slip.collection.repository.CollectionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectionServiceImpl implements CollectionService {

    private final ModelMapper modelMapper;
    private final CollectionRepository collectionRepository;

    @Autowired
    public CollectionServiceImpl(ModelMapper modelMapper, CollectionRepository collectionRepository) {
        this.modelMapper = modelMapper;
        this.collectionRepository = collectionRepository;
    }

    @Override
    public List<Collection> findCollectionList() {
        List<Collection> collectionList = collectionRepository.findAll();

        return collectionList.stream().map(collection -> modelMapper
                        .map(collection, Collection.class))
                .collect(Collectors.toList());
    }
}
