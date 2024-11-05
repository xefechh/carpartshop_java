package ee.ivkhkdev.carpartshop.services;

import ee.ivkhkdev.carpartshop.model.Product;
import ee.ivkhkdev.carpartshop.interfaces.Repository;
import ee.ivkhkdev.carpartshop.helpers.AppHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductService productService;
    private Repository<Product> mockProductRepository;
    private AppHelper mockAppHelper;

    @BeforeEach
    void setUp() {
        // Mock dependencies
        mockProductRepository = Mockito.mock(Repository.class);
        mockAppHelper = Mockito.mock(AppHelper.class);

        // Initialize ProductService with the mocked repository
        productService = new ProductService(mockAppHelper, mockProductRepository);
    }

    @Test
    void testAddProductSuccess() {
        // Prepare: create a product and mock repository behavior
        Product mockProduct = new Product("Test Product", 99.99f);
        when(mockAppHelper.createProduct()).thenReturn(mockProduct);

        // Execute the method
        productService.addProduct();

        // Verify that the product was saved to the repository
        verify(mockProductRepository, times(1)).save(mockProduct);
    }

    @Test
    void testAddProductFailureWhenProductIsNull() {
        // Mock that the product creation returns null
        when(mockAppHelper.createProduct()).thenReturn(null);

        // Execute the method
        productService.addProduct();

        // Verify that the repository's save method was not called
        verify(mockProductRepository, never()).save(any());
    }

    @Test
    void testAddProductExceptionHandling() {
        // Prepare: create a product and throw an exception when saving
        Product mockProduct = new Product("Test Product", 99.99f);
        when(mockAppHelper.createProduct()).thenReturn(mockProduct);
        doThrow(new RuntimeException("Save error")).when(mockProductRepository).save(mockProduct);

        // Execute the method
        productService.addProduct();

        // Ensure no exception is thrown and verify that save was called
        verify(mockProductRepository, times(1)).save(mockProduct);
    }

    @Test
    void testListProducts() {
        // Prepare a list of products and mock repository behavior
        List<Product> mockProductList = List.of(
                new Product("Product 1", 20.0f),
                new Product("Product 2", 30.0f)
        );
        when(mockProductRepository.load()).thenReturn(mockProductList);

        // Execute the method
        List<Product> result = productService.listProducts();

        // Verify the result
        assertEquals(mockProductList, result);
        verify(mockProductRepository, times(1)).load();
    }
}

