package dev.patika.definexjavaspringbootbootcamp2025.week6.introductiontointegrationtesting;

import dev.patika.definexjavaspringbootbootcamp2025.week6.introductiontointegrationtesting.entities.Product;
import dev.patika.definexjavaspringbootbootcamp2025.week6.introductiontointegrationtesting.repositories.ProductRepository;
import dev.patika.definexjavaspringbootbootcamp2025.week6.introductiontointegrationtesting.services.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ComponentScan(basePackages = "dev.patika.definexjavaspringbootbootcamp2025.week6.introductiontointegrationtesting")
@DataJpaTest
public class ProductServiceIntegrationTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    private UUID testGetId;

    @BeforeEach
    void setUp() {
        Product product = new Product();
        product.setName("Test Product");
        product = entityManager.persist(product);
        testGetId = product.getId();
        entityManager.flush();
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    void testSaveProduct() {
        Product product = new Product();
        product.setName("New Product");

        Product savedProduct = productService.saveProduct(product);

        assertNotNull(savedProduct);
        assertEquals("New Product", savedProduct.getName());
    }

    @Test
    void testGetAllProducts() {
        List<Product> allProducts = productService.getAllProducts();

        assertEquals(1, allProducts.size());
    }
}
