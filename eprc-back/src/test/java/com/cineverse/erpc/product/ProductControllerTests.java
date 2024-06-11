package com.cineverse.erpc.product;

import com.cineverse.erpc.product.aggregate.Product;
import com.cineverse.erpc.product.dto.ProductDTO;
import com.cineverse.erpc.product.repository.ProductRepository;
import com.cineverse.erpc.product.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductControllerTests {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @Autowired
    public ProductControllerTests(ProductService productService,
                                  ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @Test
    @Transactional
    @DisplayName("품목 전체 조회 테스트")
    public void getProductList() {
        List<Product> actualProductList = productRepository.findAll();
        List<Product> testProductList = productService.findProductList();

        assertThat(actualProductList.size()).isEqualTo(testProductList.size());
        assertThat(testProductList).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("품목 단일 조회 테스트")
    public void fineProductById() {
        Product testProduct = productRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 품목입니다."));

        ProductDTO product = productService.findProductById(1);

        assertThat(product.getProductMainCategory()).isEqualTo(testProduct.getProductMainCategory());
        assertThat(product.getProductSubCategory()).isEqualTo(testProduct.getProductSubCategory());
        assertThat(product.getProductCode()).isEqualTo(testProduct.getProductCode());
        assertThat(product.getProductName()).isEqualTo(testProduct.getProductName());
        assertThat(product.getProductUnit()).isEqualTo(testProduct.getProductUnit());
        assertThat(product.getProductPrice()).isEqualTo(testProduct.getProductPrice());
        assertThat(product.getProductStatus()).isEqualTo(testProduct.getProductStatus());
        assertThat(product.getProductInventory()).isEqualTo(testProduct.getProductInventory());
    }
}