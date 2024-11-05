package ee.ivkhkdev.carpartshop.services;

import ee.ivkhkdev.carpartshop.model.Customer;
import ee.ivkhkdev.carpartshop.interfaces.Repository;
import ee.ivkhkdev.carpartshop.helpers.AppHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    private CustomerService customerService;
    private Repository<Customer> mockCustomerRepository;
    private AppHelper mockAppHelper;

    @BeforeEach
    void setUp() {
        // Mock dependencies
        mockCustomerRepository = Mockito.mock(Repository.class);
        mockAppHelper = Mockito.mock(AppHelper.class);

        // Initialize CustomerService with the mocked repository
        customerService = new CustomerService(mockAppHelper, mockCustomerRepository);
    }

    @Test
    void testAddCustomerSuccess() {
        // Prepare: create a customer and mock repository behavior
        Customer mockCustomer = new Customer("John Doe");
        when(mockAppHelper.createCustomer()).thenReturn(mockCustomer);

        // Execute the method
        customerService.addCustomer();

        // Verify that the customer was saved to the repository
        verify(mockCustomerRepository, times(1)).save(mockCustomer);
    }

    @Test
    void testAddCustomerFailureWhenCustomerIsNull() {
        // Mock that the customer creation returns null
        when(mockAppHelper.createCustomer()).thenReturn(null);

        // Execute the method
        customerService.addCustomer();

        // Verify that the repository's save method was not called
        verify(mockCustomerRepository, never()).save(any());
    }

    @Test
    void testListCustomers() {
        // Prepare a list of customers and mock repository behavior
        List<Customer> mockCustomerList = List.of(
                new Customer("John Doe"),
                new Customer("Jane Doe")
        );
        when(mockCustomerRepository.load()).thenReturn(mockCustomerList);

        // Execute the method
        List<Customer> result = customerService.listCustomers();

        // Verify the result
        assertEquals(mockCustomerList, result);
        verify(mockCustomerRepository, times(1)).load();
    }
}

