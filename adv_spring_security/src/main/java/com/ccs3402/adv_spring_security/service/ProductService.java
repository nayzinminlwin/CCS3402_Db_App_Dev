package com.ccs3402.adv_spring_security.service;

import com.ccs3402.adv_spring_security.model.Product;
import com.ccs3402.adv_spring_security.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void initDemoData() {
        if (productRepository.count() == 0) {
            productRepository.save(new Product("Gaming Laptop", 1299.99, "High-performance gaming laptop with RTX 4070."));
            productRepository.save(new Product("Wireless Mouse", 49.99, "Ergonomic wireless mouse with adjustable DPI."));
            productRepository.save(new Product("Mechanical Keyboard", 89.99, "RGB backlit mechanical keyboard with blue switches."));
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}
