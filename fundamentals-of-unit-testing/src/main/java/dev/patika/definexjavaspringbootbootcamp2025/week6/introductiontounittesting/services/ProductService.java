package dev.patika.definexjavaspringbootbootcamp2025.week6.introductiontounittesting.services;

import org.springframework.stereotype.Service;

import dev.patika.definexjavaspringbootbootcamp2025.week6.introductiontounittesting.entities.Product;
import dev.patika.definexjavaspringbootbootcamp2025.week6.introductiontounittesting.repositories.ProductRepository;
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

    public Product saveProduct(Object product) {
        if (((Product)product).getName().length() > 10) {
            throw new RuntimeException("product name too long");
        }
        return productRepository.save((Product)product);
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
