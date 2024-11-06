package ee.ivkhkdev.phoneshop.services;

import ee.ivkhkdev.phoneshop.model.Customer;
import ee.ivkhkdev.phoneshop.model.Product;
import ee.ivkhkdev.phoneshop.model.PurchasedProduct;
import ee.ivkhkdev.phoneshop.repositories.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.*;

class PurchaseServiceTest {

    private PurchaseService purchaseService;
    private Storage<PurchasedProduct> mockStorage;

    @BeforeEach
    void setUp() {
        // Create mock of the Storage class
        mockStorage = Mockito.mock(Storage.class);

        // Pass mockStorage to the PurchaseService constructor
        purchaseService = new PurchaseService(mockStorage);
    }

    @Test
    void testPurchaseProduct() {
        // Prepare input
        Customer customer = new Customer("John Doe");
        Product product = new Product("Product A", 19.99f);

        // Call method
        purchaseService.purchaseProduct(customer, product);

        // Verify interaction with save method
        verify(mockStorage, times(1)).save(any(PurchasedProduct.class));
    }

    @Test
    void testGetPurchasedProducts() {
        // Prepare mock behavior for load() method
        List<PurchasedProduct> mockPurchaseList = List.of(
                new PurchasedProduct(new Customer("John Doe"), new Product("Product A", 19.99f)),
                new PurchasedProduct(new Customer("Jane Smith"), new Product("Product B", 29.99f))
        );
        when(mockStorage.load()).thenReturn(mockPurchaseList);

        // Call method
        List<PurchasedProduct> purchases = purchaseService.getPurchasedProducts();

        // Verify results
        verify(mockStorage, times(1)).load();
        assert purchases.size() == 2;
    }
}
