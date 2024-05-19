package com.cineverse.erpc.product.controller;

import com.cineverse.erpc.product.aggregate.Product;
import com.cineverse.erpc.product.dto.ProductDTO;
import com.cineverse.erpc.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /* 품목 전체 조회 */
    @GetMapping("")
    public List<Product> getProductList() {
        List<Product> productList = productService.findProductList();

        return productList;
    }

    /* 품목 단일 조회 */
    @GetMapping("/{productId}")
    public ProductDTO fineProductById(@PathVariable Long productId) {
        ProductDTO product = productService.findProductById(productId);

        return product;
    }
}

