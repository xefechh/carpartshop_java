package ee.ivkhkdev.carpartshop.services;

import ee.ivkhkdev.carpartshop.model.Product;
import ee.ivkhkdev.carpartshop.repositories.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductService productService;
    private Storage<Product> mockStorage;

    @BeforeEach
    void setUp() {
        // Create mock of the Storage class
        mockStorage = Mockito.mock(Storage.class);

        // Pass mockStorage to the ProductService constructor
        productService = new ProductService(mockStorage);
    }

    @Test
    void testAddProduct() {
        // Prepare input
        String productName = "Product A";
        float productPrice = 19.99f;

        // Call method
        productService.addProduct(productName, productPrice);

        // Verify that storage.save() was called once with a Product object
        verify(mockStorage, times(1)).save(any(Product.class));
    }

    @Test
    void testGetProducts() {
        // Prepare mock behavior for load() method
        List<Product> mockProductList = List.of(new Product("Product A", 19.99f), new Product("Product B", 29.99f));
        when(mockStorage.load()).thenReturn(mockProductList);

        // Call method
        List<Product> products = productService.getProducts();

        // Verify results
        verify(mockStorage, times(1)).load();
        assert products.size() == 2;
    }
}
