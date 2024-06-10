package com.cineverse.erpc.slip;

import com.cineverse.erpc.slip.collection.aggregate.Collection;
import com.cineverse.erpc.slip.collection.repository.CollectionRepository;
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
    private final CollectionRepository collectionRepository;

    @Autowired
    public CollectionControllerTests(CollectionService collectionService,
                                     CollectionRepository collectionRepository) {
        this.collectionService = collectionService;
        this.collectionRepository = collectionRepository;
    }

    @Test
    @Transactional
    @DisplayName("수금 전체조회 테스트")
    public void findCollectionList() {
        List<Collection> actualCollectionList = collectionRepository.findAll();
        List<Collection> testCollectionList = collectionService.findCollectionList();

        assertThat(actualCollectionList.size()).isEqualTo(testCollectionList.size());
        assertThat(testCollectionList).isNotEmpty();
    }
}