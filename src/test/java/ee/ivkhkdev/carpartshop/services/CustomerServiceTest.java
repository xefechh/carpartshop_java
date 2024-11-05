package ee.ivkhkdev.carpartshop.services;

import ee.ivkhkdev.carpartshop.model.Customer;
import ee.ivkhkdev.carpartshop.repositories.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private CustomerService customerService;
    private Storage<Customer> mockStorage;

    @BeforeEach
    void setUp() {
        // Create mock of the Storage class
        mockStorage = Mockito.mock(Storage.class);

        // Pass mockStorage to the CustomerService constructor
        customerService = new CustomerService(mockStorage);
    }

    @Test
    void testAddCustomer() {
        // Prepare input
        String customerName = "John Doe";

        // Call method
        customerService.addCustomer(customerName);

        // Verify that storage.save() was called once with a Customer object
        verify(mockStorage, times(1)).save(any(Customer.class));
    }

    @Test
    void testGetCustomers() {
        // Prepare mock behavior for load() method
        List<Customer> mockCustomerList = List.of(new Customer("John Doe"));
        when(mockStorage.load()).thenReturn(mockCustomerList);

        // Call method
        List<Customer> customers = customerService.getCustomers();

        // Verify that load() was called once
        verify(mockStorage, times(1)).load();

        // Assert that the list is returned as expected
        assert customers.size() == 1;
        assert customers.get(0).name().equals("John Doe");
    }
}
