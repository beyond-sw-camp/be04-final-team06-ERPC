package com.cineverse.erpc.product.service;

import com.cineverse.erpc.product.aggregate.Product;
import com.cineverse.erpc.product.dto.ProductDTO;
import com.cineverse.erpc.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findProductList() {
        List<Product> productList = productRepository.findAll();

        return productList.stream().map(product -> modelMapper
                        .map(product, Product.class))
                    .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findProductById(long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(EntityNotFoundException::new);

        productRepository.save(product);
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

        return productDTO;
    }
}
