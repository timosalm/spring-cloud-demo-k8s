package com.example.productservice.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductApplicationService {

    @Value("${product-service.product-names}")
    private List<String> productNames;

    List<Product> fetchProducts() {
        return productNames.stream()
                .map(name -> Product.create((long) (productNames.indexOf(name) + 1), name))
                .collect(Collectors.toList());
    }
}
