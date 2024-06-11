package com.cineverse.erpc.product.controller;

import com.cineverse.erpc.product.aggregate.Product;
import com.cineverse.erpc.product.dto.ProductDTO;
import com.cineverse.erpc.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @GetMapping("")
    @Operation(summary = "품목 전체 조회", description = "등록된 모든 품목을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<Product> getProductList() {
        return productService.findProductList();
    }

    @GetMapping("/{productId}")
    @Operation(summary = "품목 단일 조회", description = "특정 ID를 통해 품목을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "품목을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ProductDTO findProductById(
            @Parameter(description = "품목 ID", required = true) @PathVariable Long productId) {
        return productService.findProductById(productId);
    }
}
