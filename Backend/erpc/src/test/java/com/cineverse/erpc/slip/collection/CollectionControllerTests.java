package com.cineverse.erpc.slip.collection;

import com.cineverse.erpc.slip.collection.aggregate.Collection;
import com.cineverse.erpc.slip.collection.service.CollectionService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CollectionControllerTests {

    private final CollectionService collectionService;

    @Autowired
    public CollectionControllerTests(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @Test
    @Transactional
    @DisplayName("수금 전체조회 테스트")
    public void findCollectionList() {
        List<Collection> collectionList = collectionService.findCollectionList();

        assertThat(collectionList).isNotEmpty();
    }
}