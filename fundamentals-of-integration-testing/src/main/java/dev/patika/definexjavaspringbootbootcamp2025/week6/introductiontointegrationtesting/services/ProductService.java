package dev.patika.definexjavaspringbootbootcamp2025.week6.introductiontointegrationtesting.services;

import dev.patika.definexjavaspringbootbootcamp2025.week6.introductiontointegrationtesting.entities.Product;
import dev.patika.definexjavaspringbootbootcamp2025.week6.introductiontointegrationtesting.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(UUID id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
