package ee.ivkhkdev.carpartshop.services;

import ee.ivkhkdev.carpartshop.model.Customer;
import ee.ivkhkdev.carpartshop.model.Product;
import ee.ivkhkdev.carpartshop.model.PurchasedProduct;
import ee.ivkhkdev.carpartshop.repositories.Storage;
import ee.ivkhkdev.carpartshop.interfaces.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class PurchaseServiceTest {

    private PurchaseService purchaseService;
    private Repository<PurchasedProduct> mockPurchaseRepository;

    @BeforeEach
    void setUp() {
        // Mock dependencies
        mockPurchaseRepository = Mockito.mock(Repository.class);

        // Initialize PurchaseService with the mocked repository
        purchaseService = new PurchaseService(mockPurchaseRepository);
    }

    @Test
    void testPurchaseProduct() {
        // Prepare customer and product for the test
        Customer mockCustomer = new Customer("John Doe");
        Product mockProduct = new Product("Test Product", 99.99f);
        PurchasedProduct mockPurchase = new PurchasedProduct(mockCustomer, mockProduct);

        // Execute the method
        purchaseService.purchaseProduct(mockCustomer, mockProduct);

        // Verify that the purchase was saved to the repository
        verify(mockPurchaseRepository, times(1)).save(mockPurchase);
    }
}
