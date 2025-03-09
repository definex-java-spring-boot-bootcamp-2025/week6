package dev.patika.definexjavaspringbootbootcamp2025.week6.introductiontounittesting;

import dev.patika.definexjavaspringbootbootcamp2025.week6.introductiontounittesting.entities.Product;
import dev.patika.definexjavaspringbootbootcamp2025.week6.introductiontounittesting.repositories.ProductRepository;
import dev.patika.definexjavaspringbootbootcamp2025.week6.introductiontounittesting.services.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeAll
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        product.setName("ShortName1");
        product.setPrice(10.0);

        // Mock
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.saveProduct(product);

        assertNotNull(savedProduct);
        assertEquals("ShortName1", savedProduct.getName());
        assertEquals(10.0, savedProduct.getPrice(), 0.01);

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testSaveProduct_throwsProductNameExistsException() {
        when(productRepository.save(any(Product.class))).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            productService.saveProduct(new Product());
        });

        verifyNoInteractions(productRepository);
    }

    @Test
    public void testGetAllProducts() {
        // Arrange
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(UUID.randomUUID());
        product1.setName("Test Product 1");
        product1.setPrice(5.0);
        products.add(product1);

        Product product2 = new Product();
        product2.setId(UUID.randomUUID());
        product2.setName("Test Product 2");
        product2.setPrice(15.0);
        products.add(product2);

        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<Product> allProducts = productService.getAllProducts();

        // Assert
        assertEquals(2, allProducts.size());
        assertEquals("Test Product 1", allProducts.getFirst().getName());
    }

    @Test
    public void testGetProductById() {
        UUID inpProductId = UUID.fromString("deadbeef-dead-beef-dead-beefdeadbeef");
        Product product = new Product();
        product.setId(inpProductId);
        product.setName("Test Product");

        when(productRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.of(product));

        Product foundProduct = productService.getProductById(inpProductId);

        assertNotNull(foundProduct);
        assertEquals(inpProductId, foundProduct.getId());
        assertEquals("Test Product", foundProduct.getName());

        verify(productRepository, times(1)).findById(inpProductId);
    }

    @Test
    public void testDeleteProduct() {
        UUID inpProductId = UUID.fromString("deadbeef-dead-beef-dead-beefdeadbeef");
        productService.deleteProduct(inpProductId);

        verify(productRepository, times(1)).deleteById(inpProductId);
    }
}
